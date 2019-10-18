import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

//import javax.media.opengl.GL2;
//import javax.media.opengl.GLAutoDrawable;
//import javax.media.opengl.GLCapabilities;
//import javax.media.opengl.GLEventListener;
//import javax.media.opengl.GLProfile;
//import javax.media.opengl.awt.GLCanvas;

import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

import java.util.*;
import java.io.*;

public class Anime implements GLEventListener {

	
	static boolean flag = false;
	static boolean eyeorstar = true;
	
	static int waveposition = 1;

	static double fish1x = -330;
	static double fish1y = -210;

	static double fish2x = -190;
	static double fish2y = -40;

	static double fish3x = 45;
	static double fish3y = -30;

	static double fish4x = 250;
	static double fish4y = -140;

	static double fish5x = 385;
	static double fish5y = -340;

	static LinkedList<Double> ullx = new LinkedList<Double>();
	static LinkedList<Double> ully = new LinkedList<Double>();

	static LinkedList<Double> lllx = new LinkedList<Double>();
	static LinkedList<Double> llly = new LinkedList<Double>();

	static int fishState = 1;

	// front fish color = 1f, 0f, 1f ==> 1
	// middle fish color = .5f, 1f, .5f ==> 2
	// last fish color = 1f, .5f, .75f ==> 3

	// fish number from left to right 1 2 3 4 5
	// 1= display fish
	// 2= display fish 1
	// 3= display fish
	// 4= display fish 1 2
	// 5= display fish
	// 6= display fish 1 2 3
	// 7= display fish
	// 8= display fish 2 3 4
	// 9= display fish
	// 10= display fish 3 4 5
	// 11= display fish
	// 12= display fish 4 5
	// 13= display fish
	// 14= display fish 5
	// 15= display fish
	// 16= display no fish ; make fish 1

	static float red = .68f;
	static float green = .68f;
	static float blue = .68f;
	static float alpha = 1;
	
	//144 238 144
	static float gred = .56f;
	static float ggreen = .90f;
	static float gblue = .56f;
	

	// fish first upward
	static double firstupwardcxupper = -310;
	static double firstupwardcyupper = -290;
	static double firstupwardcxlower = -410;
	static double firstupwardcylower = -190;

	// fish going up
	static double upwardcxupper = -170;
	static double upwardcyupper = -120;
	static double upwardcxlower = -270;
	static double upwardcylower = -20;

	// fish already up
	static double cxupper = 0;
	static double cyupper = -100;
	static double cxlower = 0;
	static double cylower = 40;

	// fish going down
	static double downwardcxupper = 170;
	static double downwardcyupper = -160;
	static double downwardcxlower = 270;
	static double downwardcylower = -60;

	// fish last downward
	static double lastdownwardcxupper = 300;
	static double lastdownwardcyupper = -360;
	static double lastdownwardcxlower = 400;
	static double lastdownwardcylower = -260;
	
	static double fin1x = 0;
	static double fin1y = 0;
	
	static double fin2x = 0;
	static double fin2y = 0;
	
	float birdcx = 0f;
	float birdcy = 0f;
	
	float[] xarray = {-.40f,-.42f,-.44f,-.46f,-.48f,-.52f,-.52f ,-.54f ,-.56f,-.58f,   -.62f,-.64f,-.66f,-.68f, -.70f, -.72f, -.72f};
	float[] yarray = { .32f, .34f, .36f, .37f, .36f, .34f, .32f , .30f, .28f, .30f,     .32f,.34f,  .32f,  .34f, .33f,   .32f, .31f};
	int birdindex = 0;
	
	boolean left = true;
	
	static double r = 100;

	static suncircle scl = new suncircle();
	static fishcircle fcl = new fishcircle();

	public static Animator animator;

	public static void main(String[] args) {
		Anime s = new Anime();
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(s);
		JFrame frame = new JFrame("Wave Fish Sun");
		frame.setSize(600, 600);
		frame.add(canvas);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.addGLEventListener(new Anime());
		animator = new Animator(canvas);
		animator.start();
	}
	
	void drawTree(GL2 gl,double x0, double y0, double x1){
		
		double holdcx = suncircle.cx;
		double holdcy = suncircle.cy;
		double holdr = suncircle.r;
		
		float holdred = suncircle.red;
		float holdgreen = suncircle.green;
		float holdblue = suncircle.blue;	
		
		double y1 = .1;
		
		suncircle.red = 0f;
		suncircle.green = .55f;
		suncircle.blue = .27f;
		
		suncircle.cx = (x1+.02)*600;
		suncircle.cy = (y0+.02)*600;
		suncircle.r = 30;
		suncircle.drawAndFill(gl);
		
		suncircle.cx = (x1+.02)*600;
		suncircle.cy = (y0+.06)*600;
		suncircle.r = 30;
		suncircle.drawAndFill(gl);
		
		suncircle.cx = (x1-.04)*600;
		suncircle.cy = (y0+.02)*600;
		suncircle.r = 30;
		suncircle.drawAndFill(gl);
		
		suncircle.cx = (x1+.08)*600;
		suncircle.cy = (y0+.02)*600;
		suncircle.r = 30;
		suncircle.drawAndFill(gl);
		
		while(y0 >= y1){
			line.drawLine(gl, x0, y0, x1, y0);
			y0 -= .002;
		}
		
		suncircle.cx = holdcx;
		suncircle.cy = holdcy;
		suncircle.r = holdr;
		
		suncircle.red = holdred;
		suncircle.green = holdgreen;
		suncircle.blue = holdblue;
		
	}

	
	void firstupwardfishfin(GL2 gl){
		fin1x = ullx.get(150);
		fin1y = ully.get(150);
		
		fin2x = lllx.get(130);
		fin2y = llly.get(130);
		
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(fin1x, fin1y);
		gl.glVertex2d(fin1x - .1, fin1y - .02);
		gl.glVertex2d(fin1x - .1, fin1y- .08);
		
		gl.glVertex2d(fin2x, fin2y);
		gl.glVertex2d(fin2x-.04,fin2y -.09 );
		gl.glVertex2d(fin2x , fin2y -.08 );
		
		gl.glEnd();
	}
	
	void upwardfishfin(GL2 gl){
		fin1x = ullx.get(150);
		fin1y = ully.get(150);
		
		fin2x = lllx.get(130);
		fin2y = llly.get(130);
		
		System.out.println(fin1x+" , "+fin1y);
		System.out.println(fin2x+" , "+fin2y);
		
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(fin1x, fin1y);
		gl.glVertex2d(fin1x - .1, fin1y - .02);
		gl.glVertex2d(fin1x - .1, fin1y- .08);
		
		gl.glVertex2d(fin2x, fin2y);
		gl.glVertex2d(fin2x-.04,fin2y -.09 );
		gl.glVertex2d(fin2x , fin2y -.08 );
		
		gl.glEnd();
	}
	
	void fishfin(GL2 gl){
		fin1x = ullx.get(60);
		fin1y = ully.get(60);
		
		fin2x = lllx.get(60);
		fin2y = llly.get(60);
		
		
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(fin1x, fin1y);
		gl.glVertex2d(fin1x - .08, fin1y + .02);
		gl.glVertex2d(fin1x - .05, fin1y + .05);
		
		gl.glVertex2d(fin2x, fin2y);
		gl.glVertex2d(fin2x - .08, fin2y - .02);
		gl.glVertex2d(fin2x - .05, fin2y - .05);
		
		gl.glEnd();
	}
	
	void downwardfishfin(GL2 gl){
		fin1x = ullx.get(150);
		fin1y = ully.get(150);
		
		fin2x = lllx.get(150);
		fin2y = llly.get(150);
		
		
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(fin1x, fin1y);
		gl.glVertex2d(fin1x-.05, fin1y+ .08);
		gl.glVertex2d(fin1x-.03, fin1y+ .13);
		
		gl.glVertex2d(fin2x, fin2y);
		gl.glVertex2d(fin2x-.1,fin2y+.07);
		gl.glVertex2d(fin2x -.1,fin2y+.02);
		
		gl.glEnd();
	}
	
	void lastdownwardfishfin(GL2 gl){
		fin1x = ullx.get(150);
		fin1y = ully.get(150);
		
		fin2x = lllx.get(150);
		fin2y = llly.get(150);
		
		
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(fin1x, fin1y);
		gl.glVertex2d(fin1x-.05, fin1y+ .08);
		gl.glVertex2d(fin1x-.03, fin1y+ .13);
		
		gl.glVertex2d(fin2x, fin2y);
		gl.glVertex2d(fin2x-.1,fin2y+.07);
		gl.glVertex2d(fin2x -.1,fin2y+.02);
		
		gl.glEnd();
	}
	
	
	void drawBird(GL2 gl){
		
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL2.GL_LINES);
		
		//=======================================
		gl.glVertex3f(birdcx, birdcy,0);
		gl.glVertex3f(birdcx+.03f, birdcy+.03f,0f);
		
		//=======================================
		gl.glVertex3f(birdcx, birdcy,0);
		gl.glVertex3f(birdcx-.03f, birdcy+.03f,0f);
		
		//=======================================
		gl.glVertex3f(birdcx-.03f, birdcy,0);
		gl.glVertex3f(birdcx+.03f, birdcy,0);
		
		//=======================================
		gl.glEnd();
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {

		try {

			GL2 gl = drawable.getGL().getGL2();
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

			gl.glClearColor(red, green, blue, alpha);
			
			//======================================TREE========
			//line.drawLine(gl, -.9, .4, .9, .4);
			
			//line.drawLine(gl, -.9, .1, .9, .1);
			
			//draw 100 lines betwn the zone
			gl.glColor3f(gred, ggreen, gblue);
			double x0=-1;
			double y0=.4;
			double x1=1;
			double y1=.4;
			while(y0 >= .1 ){
				line.drawLine(gl, x0, y0, x1, y1);
				y0 -= .002;
				y1 -= .002;
			}
			
			gl.glColor3f(0f, 1f, 0f);
			drawTree(gl,-.7,.2,-.75);
			drawTree(gl,-.3,.25,-.35);
			
			//==============================================
			
			//===========================BIRD=====================
			
			
			
			if(left){
				birdcx = xarray[birdindex];
				birdcy = yarray[birdindex];
				birdindex++;
				if(birdindex == xarray.length){
					left = false;
					birdindex = xarray.length-1;
				}
				drawBird(gl);
				
			}else{
				birdcx = xarray[birdindex];
				birdcy = yarray[birdindex];
				birdindex--;
				drawBird(gl);
				if(birdindex == -1){
					left = true;
					birdindex = 0;
				}
				
			}
			
			
			//============================================
			eyeorstar = true;

			
			gl.glColor3f(1f, 1f, 1f);
			scl.drawAndFill(gl);

			// ===============================================rayline
			// drawing=========start=====================
			double line0x = (scl.cx / 600) + .08;
			double line0y = (scl.cy / 600);

			double line1x = (scl.cx / 600) + .05;
			double line1y = (scl.cy / 600) + .08;

			double line2x = (scl.cx / 600);
			double line2y = (scl.cy / 600) + .08;

			double line3x = (scl.cx / 600) - .05;
			double line3y = (scl.cy / 600) + .08;

			double line4x = (scl.cx / 600) - .08;
			double line4y = (scl.cy / 600);

			double line5x = (scl.cx / 600) - .05;
			double line5y = (scl.cy / 600) - .08;

			double line6x = (scl.cx / 600);
			double line6y = (scl.cy / 600) - .08;

			double line7x = (scl.cx / 600) + .05;
			double line7y = (scl.cy / 600) - .08;

			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line0x, line0y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line1x, line1y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line2x, line2y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line3x, line3y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line4x, line4y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line5x, line5y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line6x, line6y);
			rayline.drawLine(gl, scl.cx / 600, scl.cy / 600, line7x, line7y);
			// ===============================================rayline
			// drawing=========end=====================

			// fish number from left to right 1 2 3 4 5
			// 1= display fish
			// 2= display fish 1
			// 3= display fish
			// 4= display fish 1 2
			// 5= display fish
			// 6= display fish 1 2 3
			// 7= display fish
			// 8= display fish 2 3 4
			// 9= display fish
			// 10= display fish 3 4 5
			// 11= display fish
			// 12= display fish 4 5
			// 13= display fish
			// 14= display fish 5
			// 15= display fish
			// 16= display no fish ; make fish 1

			// System.out.println("fish state is NOW "+fishState);

			// ===============================================fish
			// drawing=========start=====================

			if (fishState == 2) {
				// firstupward fish
				fcl.drawCircle(gl, firstupwardcxupper, firstupwardcyupper, r, true, 1, 1); // circle
																							// 1
				fcl.drawCircle(gl, firstupwardcxlower, firstupwardcylower, r, false, 1, 1); // circle
																							// 2
				
				firstupwardfishfin(gl);
				
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish1x, fish1y, 6); // fish 1 eye
				halfcircle.drawhalfCircle(gl, fish1x - 10, fish1y - 12, 20, 1);
				

			} else if (fishState == 4) {
				// firstupward fish
				fcl.drawCircle(gl, firstupwardcxupper, firstupwardcyupper, r, true, 1, 2); // circle
																							// 1
				fcl.drawCircle(gl, firstupwardcxlower, firstupwardcylower, r, false, 1, 2); // circle
																							// 2
				
				firstupwardfishfin(gl);
				
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish1x, fish1y, 6); // fish 1 eye
				halfcircle.drawhalfCircle(gl, fish1x - 10, fish1y - 12, 20, 1);

				// upward fish
				fcl.drawCircle(gl, upwardcxupper, upwardcyupper, r, true, 1, 1); // circle
																					// 1
				fcl.drawCircle(gl, upwardcxlower, upwardcylower, r, false, 1, 1); // circle
																					// 2
				upwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish2x, fish2y, 6); // fish 2 eye
				halfcircle.drawhalfCircle(gl, fish2x - 10, fish2y - 12, 20, 2);

			} else if (fishState == 6) {
				// firstupward fish
				fcl.drawCircle(gl, firstupwardcxupper, firstupwardcyupper, r, true, 1, 3); // circle
																							// 1
				fcl.drawCircle(gl, firstupwardcxlower, firstupwardcylower, r, false, 1, 3); // circle
																							// 2
				firstupwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish1x, fish1y, 6); // fish 1 eye
				halfcircle.drawhalfCircle(gl, fish1x - 10, fish1y - 12, 20, 1);

				// upward fish
				fcl.drawCircle(gl, upwardcxupper, upwardcyupper, r, true, 1, 2); // circle
																					// 1
				fcl.drawCircle(gl, upwardcxlower, upwardcylower, r, false, 1, 2); // circle
																					// 2
				upwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish2x, fish2y, 6); // fish 2 eye
				halfcircle.drawhalfCircle(gl, fish2x - 10, fish2y - 12, 20, 2);

				// fish
				fcl.drawCircle(gl, cxupper, cyupper, r, true, 2, 1); // circle 1
				fcl.drawCircle(gl, cxlower, cylower, r, false, 2, 1); // circle 2
				
				fishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish3x, fish3y, 6); // fish 3 eye
				halfcircle.drawhalfCircle(gl, fish3x - 15, fish3y + 3, 20, 3);
				

			} else if (fishState == 8) {
				// upward fish
				fcl.drawCircle(gl, upwardcxupper, upwardcyupper, r, true, 1, 3); // circle
																					// 1
				fcl.drawCircle(gl, upwardcxlower, upwardcylower, r, false, 1, 3); // circle
																					// 2
				
				upwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish2x, fish2y, 6); // fish 2 eye
				halfcircle.drawhalfCircle(gl, fish2x - 10, fish2y - 12, 20, 2);

				// fish
				fcl.drawCircle(gl, cxupper, cyupper, r, true, 2, 2); // circle 1
				fcl.drawCircle(gl, cxlower, cylower, r, false, 2, 2); // circle 2
				
				fishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish3x, fish3y, 6); // fish 3 eye
				halfcircle.drawhalfCircle(gl, fish3x - 15, fish3y + 3, 20, 3);
				
				
				// downward fish
				fcl.drawCircle(gl, downwardcxupper, downwardcyupper, r, true, 3, 1); // circle
																						// 1
				fcl.drawCircle(gl, downwardcxlower, downwardcylower, r, false, 3, 1); // circle 2
				
				downwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish4x, fish4y, 6); // fish 4 eye
				halfcircle.drawhalfCircle(gl, fish4x - 10, fish4y + 12, 20, 4);

			} else if (fishState == 10) {
				// fish
				fcl.drawCircle(gl, cxupper, cyupper, r, true, 2, 3); // circle 1
				fcl.drawCircle(gl, cxlower, cylower, r, false, 2, 3); // circle 2
				
				fishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish3x, fish3y, 6); // fish 3 eye
				halfcircle.drawhalfCircle(gl, fish3x - 15, fish3y + 3, 20, 3);

				// downward fish
				fcl.drawCircle(gl, downwardcxupper, downwardcyupper, r, true, 3, 2); // circle
																						// 1
				fcl.drawCircle(gl, downwardcxlower, downwardcylower, r, false, 3, 2); // circle 2
				
				downwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish4x, fish4y, 6); // fish 4 eye
				halfcircle.drawhalfCircle(gl, fish4x - 10, fish4y + 12, 20, 4);

				// lastdownward fish
				fcl.drawCircle(gl, lastdownwardcxupper, lastdownwardcyupper, r, true, 3, 1); // circle
																								// 1
				fcl.drawCircle(gl, lastdownwardcxlower, lastdownwardcylower, r, false, 3, 1); // circle 2
				
				lastdownwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish5x, fish5y, 6); // fish 5 eye
				halfcircle.drawhalfCircle(gl, fish5x - 10, fish5y + 8, 20, 4);

			} else if (fishState == 12) {
				// downward fish
				fcl.drawCircle(gl, downwardcxupper, downwardcyupper, r, true, 3, 3); // circle
																						// 1
				fcl.drawCircle(gl, downwardcxlower, downwardcylower, r, false, 3, 3); // circle
				
				downwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish4x, fish4y, 6); // fish 4 eye
				halfcircle.drawhalfCircle(gl, fish4x - 10, fish4y + 12, 20, 4);

				// lastdownward fish
				fcl.drawCircle(gl, lastdownwardcxupper, lastdownwardcyupper, r, true, 3, 2); // circle
																								// 1
				fcl.drawCircle(gl, lastdownwardcxlower, lastdownwardcylower, r, false, 3, 2); // circle
				
				lastdownwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish5x, fish5y, 6); // fish 5 eye
				halfcircle.drawhalfCircle(gl, fish5x - 10, fish5y + 8, 20, 4);

			} else if (fishState == 14) {
				// lastdownward fish
				fcl.drawCircle(gl, lastdownwardcxupper, lastdownwardcyupper, r, true, 3, 3); // circle
																								// 1
				fcl.drawCircle(gl, lastdownwardcxlower, lastdownwardcylower, r, false, 3, 3); // circle
				
				lastdownwardfishfin(gl);
				for (int i = 0; i < ullx.size(); i++) {
					line.drawLine(gl, ullx.get(i), ully.get(i), lllx.get(i), llly.get(i));
				}
				ullx.clear();
				ully.clear();
				lllx.clear();
				llly.clear();
				normalcircle.fill(gl, fish5x, fish5y, 6); // fish 5 eye
				halfcircle.drawhalfCircle(gl, fish5x - 10, fish5y + 8, 20, 4);

			} else if (fishState == 16) {
				// no fish

			}
			if (fishState == 16) {
				fishState = 1;
			} else {
				fishState++;
			}

			// ===============================================fish
			// drawing=========end=====================
			
			
			
			
			if (waveposition == 2) {
				
				wave.drawandfillWave(gl, -50, 0,3); //  
				wave.drawandfillWave(gl, -100, 0,2); // 
				wave.drawandfillWave(gl, 0, 0,1); // normal still wave
				
				
				// ======================================================
				if (scl.cy / 600 <= -.6) {
					eyeorstar = false;
					normalcircle.fill(gl, -350, 270, 4);
					normalcircle.fill(gl, -450, 370, 5);
					normalcircle.fill(gl, -550, 470, 4);
					normalcircle.fill(gl, -500, 550, 5);
					normalcircle.fill(gl, -330, 430, 7);
					normalcircle.fill(gl, -230, 430, 7);
					normalcircle.fill(gl, -100, 500, 7);
					normalcircle.fill(gl, -30, 400, 8);
					normalcircle.fill(gl, 50, 540, 8);

				}
				// ===================================================

				// color = 1 blue
				// color = 2 that color
				
			} else if (waveposition == 4) {
				wave.drawandfillWave(gl, -50, 0,3); 
				wave.drawandfillWave(gl, -100, 0,1);
				wave.drawandfillWave(gl, 0, 0,2);
				
				if (scl.cy / 600 <= -.6) {
					eyeorstar = false;
					normalcircle.fill(gl, -420, 480, 5);
					normalcircle.fill(gl, -320, 540, 6);
					normalcircle.fill(gl, -280, 350, 6);
					normalcircle.fill(gl, -230, 430, 5);
					normalcircle.fill(gl, 100, 470, 8);
					normalcircle.fill(gl, -80, 300, 8);
				}

			}

			waveposition++;
			if (waveposition == 5) {
				waveposition = 1;

			}

			// line.drawLine(gl, -.9, .5, .9, .5); // 1
			// line.drawLine(gl, -.9, .1, .9, .1); // 2
			// line.drawLine(gl, -.9, -.4, .9, -.4); // 3
			// line.drawLine(gl, -.9, -.5, .9, -.5); // 4
			// line.drawLine(gl, -.9, -.7, .9, -.7); // 5
			// line.drawLine(gl, -.9, -.6, .9, -.6); // 6

			if (flag) {
				// flag = true upward movement
				scl.cx -= 1;
				scl.cy += 10;
				red += .009;
				green += .009;
				blue += .009;
				suncircle.green += .005;
				suncircle.blue += .009;

			} else {
				// flag = false downward movement
				scl.cx += 1;
				scl.cy -= 10;
				red -= .009;
				green -= .009;
				blue -= .009;

				suncircle.green -= .005;
				suncircle.blue -= .009;

			}

			if (scl.cy / 600 < -.75) {
				flag = true;

			}
			if (scl.cy / 600 > .6) {
				flag = false;

			}

			Thread.sleep(500);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void dispose(GLAutoDrawable drawable) {

	}

	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}

}