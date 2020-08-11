from bs4 import BeautifulSoup
from requests import get
import time


class GetUserData:

    @staticmethod
    def get_ids():
        pass
        id_df = ""
        return id_df

    @staticmethod
    def form_url(base_url, user_id):
        time.sleep(1)
        user_id = str(user_id)
        user_url = base_url + user_id
        response = get(user_url)
        html_soup = BeautifulSoup(response.content, 'lxml')
        return html_soup

    @staticmethod
    def get_com_profile(user_link):
        time.sleep(1)
        user_link = str(user_link)
        response = get(user_link)
        html_soup = BeautifulSoup(response.content, 'lxml')
        return html_soup

    @staticmethod
    def get_user(html_soup):
        user_elements = []
        score_text = ""
        profile = ""
        community_list = html_soup.find_all('div', class_='profile-communities')
        community_soup = BeautifulSoup(str(community_list), 'lxml')
        for element in community_soup:
            community = element.find_all('a', class_='site-hyperlink')
            for link in community:
                if link.get('title') == "Stack Overflow":
                    profile = link.get('href')
                else:
                    title = None
                #print(title)
        reputation = html_soup.find_all('div', class_='my12 fw-normal lh-sm')
        reputation_soup = BeautifulSoup(str(reputation), 'lxml')
        for element in reputation_soup:
            score = element.find_all('div', class_='grid--cell fs-title fc-dark')
            for text in score:
                score_text = text.text
                score_text = score_text.replace(',', '')
                score_text = int(score_text)
                #print(score_text)

        stats = html_soup.find_all('div', class_='grid gs12')
        stats_soup = BeautifulSoup(str(stats), 'lxml')
        for element in stats_soup:
            stat_elements = element.find_all('div', class_='grid--cell fs-body3 fc-dark fw-bold')
            for element_1 in stat_elements:
                element = element_1.text
                element = element.replace(',', '')
                element = element.replace('~', '')
                element = element.replace('.', '')
                if element.endswith('k') or element.endswith('m'):
                    if element.endswith('k'):
                        element = element.replace('k', '')
                        element = int(element) * 1000
                    else:
                        element = element.replace('m', '')
                        element = int(element) * 100000
                else:
                    element = int(element)

                user_elements.append(element)
            #print(user_elements)

        return user_elements, score_text, profile
