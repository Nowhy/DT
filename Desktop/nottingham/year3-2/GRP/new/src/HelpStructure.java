class Move{
	public int from;
	public int dest;
};

class Recorder{
	public Move m ;
	public Recorder() {
		m = new Move();
	}
};

class History{
	public Move m ;
	public int capture;

	public History() {
		m = new Move();
	}
} ;
