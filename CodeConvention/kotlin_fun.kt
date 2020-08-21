fun main{}
val ad = listOf<Book>(
        Book(&quot;1&quot;,&quot;11&quot;,&quot;12&quot;),
        Book(&quot;2&quot;, &quot;21&quot;,&quot;22&quot;)
    ) as MutableList<Book>

    recyclerRecommended.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = DataAdapter(ad)
    }
