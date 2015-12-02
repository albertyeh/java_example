public class thread_and_handler_framework extends Activity {
	Thread countToTen;
	TextView txtCount;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        txtCount = (TextView) findViewById(R.id.txtCount);
         
        countToTen = new CountToTen();
        countToTen.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
   
		if (countToTen != null) {
			if (!countToTen.isInterrupted()) {
				countToTen.interrupt();
			}
		}
	}
 

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            txtCount.setText(Integer.toString(msg.getData().getInt("count", 0)));
        }
    };
     
    class CountToTen extends Thread {
         @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    Bundle countBundle = new Bundle();
                    countBundle.putInt("count", i+1);
      
                    Message msg = new Message();
                    msg.setData(countBundle);
      
                    mHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

