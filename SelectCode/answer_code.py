import re
import pandas as pd
from SelectCode.DataBaseConnection import DataBaseConnection


def main():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)

    answer_df = DataBaseConnection.answer_code(database_connection, db)
    for chunk in answer_df:

        answer_code_df = pd.DataFrame(columns=['id', 'code_snippet', 'owner_user_id', 'parent_id', 'post_type_id', 'score', 'language'])
        for row in chunk.itertuples():
            id = row[1]
            body = row[2]
            creation_date = row[3]
            owner_user_id = row[4]
            parent_id = row[5]
            post_type_id = row[6]
            score = row[7]

            pattern = r'<code>(.*?)</code>'
            code = re.findall(pattern, body, flags=re.DOTALL)

            for snippet in code:
                new_row = {'id': id, 'code_snippet': snippet,  'creation_date': creation_date, 'owner_user_id': owner_user_id, 'parent_id': parent_id, 'post_type_id':post_type_id, 'score': score, 'language': ""}
                answer_code_save = answer_code_df.append(new_row, ignore_index=True)
                DataBaseConnection.save_answer_code(database_connection, db, answer_code_save)


if __name__ == "__main__":
    main()
