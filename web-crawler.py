import requests
from bs4 import BeautifulSoup
import os
import re
from time import sleep
clear = lambda: os.system('cls')
clear()
WEBLENGTH = 1500
INITIALLINK = 'https://www.cbc.ca/news'
ListOfWebLinks = []
ListOfWebLinks.append(INITIALLINK)
FileOne = open("websites.txt","w")
FileOne.write(INITIALLINK+'\n')
def webCrawler(UrlLink):
    try:
        url = UrlLink
        code = requests.get(url)
        plain = code.text
        s = BeautifulSoup(plain, "html.parser")
        for link in s.findAll('a'):
            tet_2 = link.get('href')
            if tet_2 != None:
                find = re.findall("http",tet_2)
                if find:
                    if len(ListOfWebLinks)<WEBLENGTH:
                        if tet_2 not in ListOfWebLinks:
                            FileOne.write(tet_2+'\n')
                            ListOfWebLinks.append(tet_2)
    except requests.exceptions.ConnectionError:
        sleep(5)
print("Starting web crawling\n\n")
for i in ListOfWebLinks:
    if len(ListOfWebLinks)<WEBLENGTH:
        webCrawler(i)
    else:
        break
print(ListOfWebLinks)
print(len(ListOfWebLinks))
print("\n\nEnd of web crawling")

FileOne.close()