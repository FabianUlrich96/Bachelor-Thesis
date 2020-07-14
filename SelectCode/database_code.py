import re

from SelectCode.DataBaseConnection import DataBaseConnection


def main():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    answer_df = DataBaseConnection.answer_code(database_connection, db)

    question_df = DataBaseConnection.question_code(database_connection, db)
    print(question_df['body'])

    for row in question_df.itertuples():
        body = row[3]
        pattern = r'<code>(.*?)</code>'
        code = re.findall(pattern, body, flags=re.DOTALL)
        print(code)
    s = """<p>I am rendering the labels in <code>onDraw()</code>, not with custom views. I also tried with a <code>TextView</code> the size of the labels and using <code>Gravity.CENTER</code>, but this gave the same results. Also note that I tried <code>Align.Center</code> too.</p>

<p>Code:</p>

<pre><code>usedPaint.setTextAlign(Align.LEFT); //Also tried it with Center
Rect textBounds = new Rect();
usedPaint.getTextBounds(infoText, 0, infoText.length(), textBounds);
float textWidth = usedPaint.measureText(infoText);
canvas.drawText(infoText, circleX - (0.5F * textWidth),  circleY - ((usedPaint.descent() + usedPaint.ascent())/2),
                usedPaint);
canvas.drawRect((circleX - (0.5F* textWidth)), circleY - (0.5F * textBounds.height()), circleX + (0.5F * textWidth), circleY + (0.5F * textBounds.height()), otherPaint);
</code></pre>

<p>The drawn box's width is the result of <code>measureText</code>. At the <code>E</code> character you see that somehow it measures some whitespace on the left, making the character drift off to the right.</p>

<p>This is using the standard font on Android 4.0.3. Using a custom TTF font results in the same kind of issues, but different for each character.</p>

<p>I am wondering what else I can do? (Besides getting over it ;) )</p>"""




if __name__ == "__main__":
    main()
