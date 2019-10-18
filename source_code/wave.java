import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

//import javax.media.opengl.GL2;
//import javax.media.opengl.GLAutoDrawable;
//import javax.media.opengl.GLCapabilities;
//import javax.media.opengl.GLEventListener;
//import javax.media.opengl.GLProfile;
//import javax.media.opengl.awt.GLCanvas;

import javax.swing.JFrame;

import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class wave {
	
	
	static double cx=-500;
	static double cy=-400;
	static double r= 100;
	
	static int divideBy = 600;
	
	static void drawandfillWave(GL2 gl, double addx, double addy, int color){
		
		
		double tempcx;
		double tempcy;
		
		tempcx = cx+addx;
		tempcy = cy-70;
		for(int j=0;j<230;j++){
			tempcx = cx+addx;
			for(int i=0;i<7;i++){
				drawCircle(gl,tempcx,tempcy,color);
				tempcx += 2*r;
			}
			tempcy -= 1;
		}
		
	}
	
	
	static void drawCircle(GL2 gl, double tcx,double tcy, int color){
		gl.glBegin(GL2.GL_POINTS);
		
		double x =  r;
		double y =  0;
		
		drawPoint(gl,x, y,tcx,tcy,color);
		double d = 5/4 - r;
		
		while(y <= x){
			if(d < 0){
				//d += (2*y+1);
				//d += (2*y+3)*4;
				d += (2*y+3);
				y++;
				//y += .001;
			}else{
				//d += 2*(y - x) + 1;
				//d += (2*y-2*x+5)*4;
				d += (2*y-2*x+5);
				y++;
				x--;
				//y += .001;
				//x -= .001;
			}
			drawPoint(gl,x, y,tcx,tcy,color);
		}
		
		gl.glEnd();
	}
	
	
	static void drawPoint(GL2 gl, double x0, double y0,double cx,double cy, int color){
		
		double x,y;
		
		if(color == 1){
			gl.glColor3f(.12f, .56f, 1f);
		}else if(color == 2){
			gl.glColor3f(.06f, .31f, .55f);
		}else if(color == 3){
			gl.glColor3f(0f, 0f, .55f);
		}
		
		// zone 0
		x=x0+cx ; y=y0+cy;
		//System.out.println("zone 0: x = "+x+" and y = "+y);
		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 1
		x=y0+cx;y=x0+cy;
		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 2
		x=-y0+cx ; y=x0+cy;
		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 3
		x=-x0+cx;y=y0+cy;
		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 4
//		x=-x0+cx;y=-y0+cy;
//		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 5
//		x=-y0+cx;y=-x0+cy;
//		gl.glVertex2d(x/divideBy, y/divideBy);
		
		
		// zone 6
//		x=y0+cx;y=-x0+cy;
//		gl.glVertex2d(x/divideBy, y/divideBy);
		
		// zone 7
//		x=x0+cx;y=-y0+cy;
//		gl.glVertex2d(x/divideBy, y/divideBy);
		
		
	}
	

	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	public void init(GLAutoDrawable drawable) {
		// method body
		// 4. drive the display() in a loop
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// method body
	}
	// end of main
}// end of classimport javax.media.opengl.GL2;