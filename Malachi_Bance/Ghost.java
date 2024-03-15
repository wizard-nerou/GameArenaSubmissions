public class Ghost {
	public String color = "WHITE";
	private Ball body;
	private Ball one;
	private Ball two;
	private Ball cornea1;
	private Ball cornea2;
	private Ball pupil1;
	private Ball pupil2;
	private GameArena arena;
	private int gridSize;
	public int size;

	public int x;
	public int y;

	public int mx = 0;
	public int my = 0;

	public int depth = 1;

	public Boolean runaway = false;

	public Ghost(GameArena a, int g, String c, int d) {
		arena = a;
		gridSize = g;
		color = c;
		size = (int)(a.getArenaWidth()/g);
		x = (int)(a.getArenaWidth() /2);
		y = (int)(a.getArenaWidth()/2);
		depth = d;
	}

	public void spawn(int i, int j) {
		System.out.println("Ghost Created");
		x = i; y = j;
		body = new Ball(x,y,size,color);
		one = new Ball(x-(size/4),y+(size/3.5),size/2,color);
		two = new Ball(x+(size/4),y+(size/3.5),size/2,color);
		cornea1 = new Ball(x-(size/4),y,size/3,"WHITE");
		cornea2 = new Ball(x+(size/4),y,size/3,"WHITE");
		pupil1 = new Ball(x-(size/4),y,size/5,"BLACK");
		pupil2 = new Ball(x+(size/4),y,size/5,"BLACK");
		arena.addBall(body);
		arena.addBall(one);
		arena.addBall(two);
		arena.addBall(cornea1);
		arena.addBall(cornea2);
		arena.addBall(pupil1);
		arena.addBall(pupil2);
	}

	private double distance(int x1, int y1, int x2, int y2) {
		return Math.hypot(Math.abs(y2-y1),Math.abs(x2-x1));
	}

	public void run() {
		runaway = true;
		arena.removeBall(body);
		arena.removeBall(one);
		arena.removeBall(two);
		arena.removeBall(cornea1);
		arena.removeBall(cornea2);
		arena.removeBall(pupil1);
		arena.removeBall(pupil2);
		body = new Ball(x,y,size,"BLUE");
		one = new Ball(x-(size/4),y+(size/3.5),size/2,"BLUE");
		two = new Ball(x+(size/4),y+(size/3.5),size/2,"BLUE");
		cornea1 = new Ball(x-(size/4),y,size/3,"WHITE");
		cornea2 = new Ball(x+(size/4),y,size/3,"WHITE");
		pupil1 = new Ball(x-(size/4),y,size/5,"BLACK");
		pupil2 = new Ball(x+(size/4),y,size/5,"BLACK");
		arena.addBall(body);
		arena.addBall(one);
		arena.addBall(two);
		arena.addBall(cornea1);
		arena.addBall(cornea2);
		arena.addBall(pupil1);
		arena.addBall(pupil2);
	}

	public void resume() {
		runaway = false;
		arena.removeBall(body);
		arena.removeBall(one);
		arena.removeBall(two);
		arena.removeBall(cornea1);
		arena.removeBall(cornea2);
		arena.removeBall(pupil1);
		arena.removeBall(pupil2);
		body = new Ball(x,y,size,color);
		one = new Ball(x-(size/4),y+(size/3.5),size/2,color);
		two = new Ball(x+(size/4),y+(size/3.5),size/2,color);
		cornea1 = new Ball(x-(size/4),y,size/3,"WHITE");
		cornea2 = new Ball(x+(size/4),y,size/3,"WHITE");
		pupil1 = new Ball(x-(size/4),y,size/5,"BLACK");
		pupil2 = new Ball(x+(size/4),y,size/5,"BLACK");
		arena.addBall(body);
		arena.addBall(one);
		arena.addBall(two);
		arena.addBall(cornea1);
		arena.addBall(cornea2);
		arena.addBall(pupil1);
		arena.addBall(pupil2);
	}

	private double assess(int ax, int ay, Pellets p, int plx, int ply, int stage) {
		int options[][] = {
			{ 0, 1},
			{ 0,-1},
			{ 1, 0},
			{-1, 0}
		};
		int best = -1;
		double bestScore = 999999;
		for (int i = 0; i < 4; i++) {
			int dx = options[i][0];
			int dy = options[i][1];
			int apx = ax+dx;
	        int apy = ay+dy;
	        if (apx > gridSize-1) {
	            apx = 0;
	        } else if (apx < 0) {
	            apx = gridSize-1;
	        }
	        if (apy > gridSize-1) {
	            apy = 0;
	        } else if (apy < 0) {
	            apy = gridSize-1;
	        }
	        if (p.map[apx][apy] != 1) {
	        	double dist;
	        	if (stage > 0) {
	        		dist = this.assess(apx,apy,p,plx,ply,stage-1);
	        	} else {
	        		int px = (int)(plx / size);
	        		int py = (int)(ply / size);
		        	dist = this.distance(apx,apy,px,py);
		        }
		        if (!runaway) {
		        	if (dist < bestScore) {
		        		bestScore = dist;
		        		best = i;
		        	}
		        } else {
		        	if (dist > bestScore) {
		        		bestScore = dist;
		        		best = i;
		        	}
		        }
	        }
	    }
	    return bestScore;
	}

	public void think(Pellets p, int plx, int ply) {
		int options[][] = {
			{ 0, 1},
			{ 0,-1},
			{ 1, 0},
			{-1, 0}
		};
		int optionPool[] = {-1,-1,-1,-1};
		int me = -1;
		int best = -1;
		double bestScore = 999999;
		for (int i = 0; i < 4; i++) {
			int dx = options[i][0];
			int dy = options[i][1];
			int apx = (int)(x / size)+dx;
	        int apy = (int)(y / size)+dy;
	        if (apx > gridSize-1) {
	            apx = 0;
	        } else if (apx < 0) {
	            apx = gridSize-1;
	        }
	        if (apy > gridSize-1) {
	            apy = 0;
	        } else if (apy < 0) {
	            apy = gridSize-1;
	        }
	        if (p.map[apx][apy] != 1) {
	        	if (dx == mx && dy == my) {
	        		me = i;
	        	}
	        	double dist = this.assess(apx,apy,p,plx,ply,depth);
	        	if (!runaway) {
		        	if (dist < bestScore) {
		        		bestScore = dist;
		        		best = i;
		        	}
		        } else {
		        	if (dist > bestScore) {
		        		bestScore = dist;
		        		best = i;
		        	}
		        }
	            optionPool[i] = i;
	        } else {
	        	dx = -mx;
	        	dy = -my;
	        }
	    }
	    int rand = (int)(Math.random()*4);
	    if (me != -1 && Math.random()>0.1) {
    		rand = me;
    	} 
    	if (best != -1 && Math.random() < 0.2 && (options[best][0] != mx*-1 || options[best][1] != my*-1)) {
    		rand = best;
    	}
	    while (true) {
	    	if (optionPool[rand] == -1) {
	    		rand = (int)(Math.random()*4);
	    		continue;
	    	}
	    	int dx = options[rand][0];
	    	int dy = options[rand][1];
	    	if (dx != mx || dy != my) {
	    		x = (x / size)*size + size/2;
            	y = (y / size)*size + size/2;
	    	}
	    	mx = dx;
	    	my = dy;
	    	break;
	    }
	}

	public void move() {
		x += mx*4;
		y += my*4;
		if (x > arena.getArenaWidth()) {
            x = 0;
        }
        if (x < 0) {
            x = arena.getArenaWidth();
        }
        if (y > arena.getArenaWidth()) {
            y = 0;
        }
        if (y < 0) {
            y = arena.getArenaWidth();
        }
		body.setXPosition(x);
		one.setXPosition(x-(size/4));
		two.setXPosition(x+(size/4));
		cornea1.setXPosition(x-(size/4));
		cornea2.setXPosition(x+(size/4));
		pupil1.setXPosition(x-(size/4));
		pupil2.setXPosition(x+(size/4));
		body.setYPosition(y);
		one.setYPosition(y+(size/3.5));
		two.setYPosition(y+(size/3.5));
		cornea1.setYPosition(y);
		cornea2.setYPosition(y);
		pupil1.setYPosition(y);
		pupil2.setYPosition(y);
	}
}