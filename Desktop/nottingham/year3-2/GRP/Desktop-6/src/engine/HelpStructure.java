/**
Help Structures, writen by Zhejie Lee
**/
package engine;
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
