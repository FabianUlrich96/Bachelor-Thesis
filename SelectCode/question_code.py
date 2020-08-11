import re
import pandas as pd
from DataBaseConnection.DataBaseConnection import DataBaseConnection


def main():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)

    question_df = DataBaseConnection.question_code(database_connection, db)
    for chunk in question_df:

        question_code_df = pd.DataFrame(columns=['id', 'tags', 'code_snippet', 'answer_count', 'comment_count', 'accepted_answer_id', 'creation_date', 'score',  'view_count', 'owner_user_id', 'favorite_count', 'post_type_id', 'language'])
        for row in chunk.itertuples():
            id = row[1]
            tags = row[2]
            body = row[3]
            answer_count = row[4]
            comment_count = row[5]
            accepted_answer_id = row[6]
            creation_date = row[7]
            score = row[8]
            view_count = row[9]
            owner_user_id = row[10]
            favorite_count = row[11]
            post_type_id = row[12]

            pattern = r'<code>(.*?)</code>'
            code = re.findall(pattern, body, flags=re.DOTALL)

            for snippet in code:
                new_row = {'id': id, 'tags': tags, 'code_snippet': snippet, 'answer_count': answer_count, 'comment_count': comment_count, 'accepted_answer_id': accepted_answer_id, 'creation_date': creation_date, 'score': score, 'view_count': view_count, 'owner_user_id': owner_user_id, 'favorite_count': favorite_count, 'post_type_id':post_type_id, 'language': ""}
                question_code_save = question_code_df.append(new_row, ignore_index=True)
                DataBaseConnection.save_question_code(database_connection, db, question_code_save)


if __name__ == "__main__":
    main()
