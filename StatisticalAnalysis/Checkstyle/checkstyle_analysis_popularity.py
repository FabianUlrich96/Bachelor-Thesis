import pandas as pd
import seaborn as sns
from matplotlib import pyplot as plt


def select_answers():
    answer_df = pd.read_csv("ru_checkstyle_answer_total_errors.csv", encoding='utf8')

    return answer_df


def select_questions():
    question_df = pd.read_csv("ru_checkstyle_question_total_errors.csv", encoding='utf8')

    return question_df


def plot_boxplot(data, file_name):
    ax = sns.boxplot(x="popularity", y="total_errors", hue="popularity", data=data, showfliers=False)
    plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0., prop={'size': 15})
    plt.xlabel("Popularity", fontsize=15)
    plt.ylabel("Errors", fontsize=15)
    plt.savefig(file_name)
    plt.show()


def main():
    answer_df = select_answers()
    question_df = select_questions()
    plot_boxplot(answer_df, "ru_checkstyle_answers_popularity.png")
    plot_boxplot(question_df, 'ru_checkstyle_questions_popularity.png')


if __name__ == "__main__":
    main()