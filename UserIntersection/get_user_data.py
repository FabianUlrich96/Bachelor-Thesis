from UserIntersection import GetUserData
import pandas as pd
from DataBaseConnection import DataBaseConnection
import sqlite3


def connect_db():
    database_connection = DataBaseConnection.DataBaseConnection()

    database_location = r'D:\StackOverflow-Backup\stackoverflow_ru.db'

    db = sqlite3.connect(database_location, timeout=10)
    #db = DataBaseConnection.DataBaseConnection.connect_database(database_connection)

    return db, database_connection


def get_ids():
    db, connection = connect_db()
    answer_user_id = DataBaseConnection.DataBaseConnection.answer_user_id(connection, db)
    question_user_id = DataBaseConnection.DataBaseConnection.question_user_id(connection, db)
    user_id = answer_user_id.append(question_user_id)
    user_id = user_id.drop_duplicates()
    return user_id


def get_user_link():
    db, connection = connect_db()
    ru_users = DataBaseConnection.DataBaseConnection.ru_user_id(connection, db)

    return ru_users


def create_df():
    df_list = []
    columns = ['replies', 'questions', 'people_affected', 'com_profile', 'reputation', 'user_id']
    df = pd.DataFrame(df_list, columns=columns)
    return df, columns


def append_df(user_elements, user_df, profile, reputation, columns, user_id):
    if len(user_elements) != 3:
        return user_df
    else:
        user_elements.append(profile)
        user_elements.append(reputation)
        user_elements.append(user_id)
        df = pd.DataFrame([user_elements], columns=columns)
        user_df = user_df.append(df)
        print(user_df)

    return user_df


def iter_user_ids(user_ids, base_url, user_df, columns):
    for row in user_ids.itertuples():
        user_id = row[1]
        soup = GetUserData.GetUserData.form_url(base_url, user_id)
        user_elements, reputation, profile = GetUserData.GetUserData.get_user(soup)
        user_df = append_df(user_elements, user_df, profile, reputation, columns,  user_id)
    print(user_df)
    return user_df


def ru_user_id(ru_users, ru_df, columns):
    for row in ru_users.itertuples():
        user_link = row[1]
        soup = GetUserData.GetUserData.get_com_profile(user_link)
        user_elements, reputation, profile = GetUserData.GetUserData.get_user(soup)
        ru_df = append_df(user_elements, ru_df, profile, reputation, columns, user_link)
        print(ru_df)
    return ru_df


def save_db(user_df, table):
    db, connection = connect_db()
    user_df.to_sql(table, con=db, index=False)


def main():
    user_df, columns = create_df()
    user_ids = get_ids()
    base_url = 'https://ru.stackoverflow.com/users/'
    user_df = iter_user_ids(user_ids, base_url, user_df, columns)
    save_db(user_df, 'ru_users')

    ru_df, columns = create_df()
    ru_links = get_user_link()
    ru_df = ru_user_id(ru_links,ru_df, columns)
    save_db(ru_df, 'ru_users_com')


if __name__ == "__main__":
    main()
