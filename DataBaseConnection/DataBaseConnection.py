import sqlite3
import pandas as pd


class DataBaseConnection:

    def connect_database(self):
        database_location = r'D:\StackOverflow-Backup\stackoverflow_current.db'

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
        df = pd.read_sql_query("select id, code_snippet from question_code_roughly_filtered", db, chunksize=100000)
        # chunksize=100000
        return df

    def answer_snippet(self, db):
        df = pd.read_sql_query("select id, code_snippet from answer_code_roughly_filtered", db, chunksize=100000)
        return df

    def save_question_classification(self, db, classification):
        classification.to_sql('question_code_classification_new', db, if_exists='append', index=False)

    def save_answer_classification(self, db, classification):
        classification.to_sql('answer_code_classification_new', db, if_exists='append', index=False)

    def select_question_java(self, db):
        df = pd.read_sql_query("select id, code_snippet from question_code_classification_new where probability > 0.75 and language = 'java'", db, chunksize=100000)
        return df

    def select_answer_java(self, db):
        df = pd.read_sql_query("select id, code_snippet from answer_code_classification_new where probability > 0.75 and language = 'java'", db, chunksize=100000)
        return df

    def select_question_kotlin(self, db):
        df = pd.read_sql_query("select id, code_snippet from question_code_classification_new where probability > 0.75 and language = 'kotlin'", db, chunksize=100000)
        return df

    def select_answer_kotlin(self, db):
        df = pd.read_sql_query("select id, code_snippet from answer_code_classification_new where probability > 0.75 and language = 'kotlin'", db, chunksize=100000)
        return df

    def answer_user_id(self, db):
        df = pd.read_sql_query(
            "select distinct owner_user_id from answer_code_roughly_filtered where owner_user_id is not null", db)

        return df

    def question_user_id(self, db):
        df = pd.read_sql_query(
            "select distinct owner_user_id from question_code_roughly_filtered where owner_user_id is not null", db)

        return df

    def ru_user_id(self, db):
        df = pd.read_sql_query(
            "select com_profile from ru_users where com_profile != ''", db)

        return df

    def save_kotlin_answer_complexity(self, db, classification):
        classification.to_sql('kotlin_answer_complexity', db, if_exists='append', index=False)

    def select_kotlin_answer_complexity(self,db):
        df = pd.read_sql_query(
            "select complexity, line_complexity from 'kotlin_answer_complexity'", db)
        return df

    def save_java_answer_complexity(self, db, classification):
        classification.to_sql('java_answer_complexity', db, if_exists='append', index=False)

    def select_java_answer_complexity(self,db):
        df = pd.read_sql_query(
            "select complexity, line_complexity from 'java_answer_complexity'", db)
        return df

    def save_kotlin_question_complexity(self, db, classification):
        classification.to_sql('kotlin_question_complexity', db, if_exists='append', index=False)

    def select_kotlin_question_complexity(self,db):
        df = pd.read_sql_query(
            "select complexity, line_complexity from 'kotlin_question_complexity'", db)
        return df

    def save_java_question_complexity(self, db, classification):
        classification.to_sql('java_question_complexity', db, if_exists='append', index=False)

    def select_java_question_complexity(self, db):
        df = pd.read_sql_query(
            "select complexity, line_complexity from 'java_question_complexity'", db)
        return df

    def save_java_question_update(self, db, update):
        update.to_sql('java_question_code_updated', db, if_exists='append', index=False)

    def save_java_answer_update(self, db, update):
        update.to_sql('java_answer_code_updated', db, if_exists='append', index=False)

    def save_kotlin_question_update(self, db, update):
        update.to_sql('kotlin_question_code_updated_2', db, if_exists='append', index=False)

    def save_kotlin_answer_update(self, db, update):
        update.to_sql('kotlin_answer_code_updated_2', db, if_exists='append', index=False)

    def select_kotlin_answer_update(self, db):
        df = pd.read_sql_query(
            "select * from 'kotlin_answer_code_updated_2'", db, chunksize=100000)
        return df

    def select_kotlin_question_update(self, db):
        df = pd.read_sql_query(
            "select * from 'kotlin_question_code_updated_2'", db, chunksize=100000)
        return df

    def select_java_answer_update(self, db):
        df = pd.read_sql_query(
            "select * from 'java_answer_code_updated' where ROWID > 155000 order by ROWID limit 45000", db, chunksize=100000)
        return df

    def select_java_question_update(self, db):
        df = pd.read_sql_query(
            "select * from 'java_question_code_updated' where ROWID > 128000 order by ROWID limit 45000", db, chunksize=100000)
        return df

    def save_ktlint_answers(self, db, update):
        update.to_sql('ktlint_answers_2', db, if_exists='append', index=False)

    def save_ktlint_questions(self, db, update):
        update.to_sql('ktlint_questions_2', db, if_exists='append', index=False)

    def save_checkstyle_answers(self, db, update):
        update.to_sql('checkstyle_answers', db, if_exists='append', index=False)

    def save_checkstyle_questions(self, db, update):
        update.to_sql('checkstyle_questions', db, if_exists='append', index=False)






