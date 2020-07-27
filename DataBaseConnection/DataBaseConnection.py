import sqlite3
import pandas as pd

class DataBaseConnection:

    def connect_database(self):
        database_location = r'C:\Database\stackoverflow_ru.db'

        db = sqlite3.connect(database_location, timeout=10)
        return db


    def connect_training_db(self):
        database_location = r'D:\OneDrive - Otto-Friedrich-Universität Bamberg\SS 20\Bachelorarbeit\Code Quality analysis\Training_data\stackoverflow_training.db'

        db = sqlite3.connect(database_location, timeout=10)
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

    def select_question_code(self, db):
        #cur = db.cursor()
        #cur.execute("SELECT  * from question_code")
        #names = [x[0] for x in cur.description]
        #rows = cur.fetchall()

        sql = """SELECT  * from question_code """
        df = pd.read_sql_query(sql, db, chunksize=100000)
        #return pd.DataFrame(rows, columns=names)
        return df

    def select_answer_code(self, db):
        sql = """SELECT  * from answer_code """
        df = pd.read_sql_query(sql, db, chunksize=100000)

        return df

    def save_final_question_code(self, db, question):
        question.to_sql('question_code_final', db, if_exists='append', index=False)

    def save_final_answer_code(self, db, question):
        question.to_sql('answer_code_final', db, if_exists='append', index=False)

    def select_xml(self, db):
        sql = """SELECT  * from xml_posts """
        df = pd.read_sql_query(sql, db, chunksize=100000)

        return df

    def save_xml_code(self, db, code):
        code.to_sql('xml_code', db, if_exists='append', index=False)

    def select_java(self, db):
        sql = """SELECT  * from java_posts """
        df = pd.read_sql_query(sql, db, chunksize=100000)

        return df

    def save_java_code(self, db, code):
        code.to_sql('java_code', db, if_exists='append', index=False)

    def select_kotlin(self, db):
        sql = """SELECT  * from kotlin_posts """
        df = pd.read_sql_query(sql, db, chunksize=100000)

        return df

    def save_kotlin_code(self, db, code):
        code.to_sql('kotlin_code', db, if_exists='append', index=False)


    def select_kotlin_code(self, db):
        sql = """SELECT  code_snippet from kotlin_code """
        df = pd.read_sql_query(sql, db)

        return df

    def select_java_code(self, db):
        sql = """SELECT  code_snippet from java_code """
        df = pd.read_sql_query(sql, db)

        return df

    def select_xml_code(self, db):
        sql = """SELECT  code_snippet from xml_code """
        df = pd.read_sql_query(sql, db)

        return df

    def update_question_language(self,db):
        cur = db.cursor()
        cur.execute("UPDATE question_code SET language = %s where id = %s ")


    def question_snippet(self, db):
        df = pd.read_sql_query("select id, code_snippet from question_code_final limit 10", db, chunksize=100000)
        return df

    def answer_snippet(self, db):
        df = pd.read_sql_query("select id, code_snippet from answer_code_final limit 10", db, chunksize=100000)
        return df

    def save_question_classification(self, db, classification):
        classification.to_sql('question_code_classification', db, if_exists='append', index=False)

    def save_answer_classification(self, db, classification):
        classification.to_sql('answer_code_classification', db, if_exists='append', index=False)
