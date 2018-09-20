

public class Test{
	public static void main(String[] argc){
		AIBoard board = new AIBoard();
		Recommender r = new Recommender();
		Search s = new Search();
		
		board.printBoard();
		System.out.println(r.getRecords());
		Thread t = new Thread(s);
		t.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board.printBoard();
		s.keepSearching = false;
		board.printBoard();
	}
}