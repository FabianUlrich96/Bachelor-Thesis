import sqlite3
import pandas as pd


class DataBaseConnection:

    def connect_database(self):
        database_location = 'D:\OneDrive - Otto-Friedrich-Universität Bamberg\SS 20\Bachelorarbeit\DataRetrieval\stackoverflow.com\stackoverflow.com database\stackoverflow.db'

        db = sqlite3.connect(database_location)
        return db

    def answer_code(self, db):
        df = pd.read_sql_query("select * from answers where body like '%<code>%' limit 50", db)
        return df

    def question_code(self, db):
        df = pd.read_sql_query("select * from questions_nokey_copy where body like '%<code>%'", db, chunksize=100000)
        return df

    def save_question_code(self, db, question):
        question.to_sql('question_code', db, if_exists='append',  index=False)
