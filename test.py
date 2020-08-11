from javac_parser import javac_parser
import re
import random
import string


stringn = '''generate_btn.setOnClickListener{
    btnClicks++
    val intent = Intent(this@MainActivity, SecondActivity::class.java)
    intent.putExtra("generate_btn_clicks", btnClicks)

        if (btnClicks == 1) {
            startActivity(intent)
        } else if (btnClicks == 2) {
            startActivity(intent)
        } else if (btnClicks == 3) {
            startActivity(intent)
            btnClicks = 0
        }
    }
}
'''

nlines = stringn.count('\n')

print(nlines)