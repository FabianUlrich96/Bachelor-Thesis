import pandas as pd


def select_answers():
    answer_df = pd.read_csv("kotlin_answer_n_line_ru.csv", encoding='utf8')
    return answer_df


def select_questions():
    question_df = pd.read_csv("kotlin_question_n_line_ru.csv", encoding='utf8')

    return question_df


def main():
    question_df = select_questions()
    answer_df = select_answers()

    answer_median = answer_df.median(axis=0)
    answer_mode = answer_df.mode(axis=0)
    answer_mean = answer_df.mean(axis=0).round(2)
    answer_std = answer_df.std(axis=0).round(2)

    question_median = question_df.median(axis=0)
    question_mode = question_df.mode(axis=0)
    question_mean = question_df.mean(axis=0).round(2)
    question_std = question_df.std(axis=0).round(2)

    print(question_std)


if __name__ == "__main__":
    main()