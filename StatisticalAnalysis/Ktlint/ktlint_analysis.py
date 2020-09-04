import pandas as pd
import seaborn as sns
from matplotlib import pyplot as plt


def select_answers():
    answer_df = pd.read_csv("ktlint_answer_score_error.csv", encoding='utf8')

    return answer_df


def select_questions():
    question_df = pd.read_csv("ktlint_question_score_error.csv", encoding='utf8')

    return question_df


def plot_boxplot(data, file_name):
    ax = sns.boxplot(x="popularity", y="total_errors", hue="popularity", data=data, showfliers=False)
    plt.xlabel("Popularity", fontsize=15)
    plt.ylabel("Errors", fontsize=15)
    plt.savefig(file_name)
    plt.show()


def main():
    answer_df = select_answers()
    question_df = select_questions()
    plot_boxplot(answer_df, "ktlint_answer_popularity.png")
    plot_boxplot(question_df, 'ktlint_question_popularity.png')


if __name__ == "__main__":
    main()