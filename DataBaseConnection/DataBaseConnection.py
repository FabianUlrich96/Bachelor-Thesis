import sqlite3
import pandas as pd


class DataBaseConnection:

    def connect_database(self):
        database_location = 'D:\OneDrive - Otto-Friedrich-Universität Bamberg\SS 20\Bachelorarbeit\DataRetrieval\stackoverflow.ru\stackoverflow.ru database\stackoverflow_ru.db'

        db = sqlite3.connect(database_location)
        return db

    def answer_code(self, db):
        df = pd.read_sql_query("select * from answers where body like '%<code>%'", db, chunksize=100000)
        return df

    def question_code(self, db):
        df = pd.read_sql_query("select * from questions_nokey where body like '%<code>%'", db, chunksize=100000)
        return df

    def save_question_code(self, db, question):
        question.to_sql('question_code', db, if_exists='append',  index=False)


    def save_answer_code(self, db, answer):
        answer.to_sql('answer_code', db, if_exists='append', index=False)
