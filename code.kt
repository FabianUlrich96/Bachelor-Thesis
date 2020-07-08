override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        CoroutineScope(Default).launch {
            runContinuousFunction()
        }
