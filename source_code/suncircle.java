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

public class suncircle {
	
	static double cx=480;
	static double cy=300;
	static double r= 30;
	
	static double noy = -.62; // below this no sun
	
	static float red = 1f;
	static float green = 1f;
	static float blue = 1f;
	
	static int divideBy = 600;
	
	static void drawAndFill(GL2 gl){
		double tempr = r;
		
		while(tempr > 0){
			drawCircle(gl,tempr);
			tempr -= 1;
		}
		
	}
	
	static void drawCircle(GL2 gl, double rr){
		gl.glBegin(GL2.GL_POINTS);
		
		double x =  rr;
		double y =  0;
		
		drawPoint(gl,x, y,cx,cy);
		double d = 5/4 - rr;
		
		//System.out.println("Beginning d is "+d);
		
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
			//System.out.println("d is "+d);
			drawPoint(gl,x, y,cx,cy);
		}
		
		gl.glEnd();
	}
	
	
	static void drawPoint(GL2 gl, double x0, double y0,double cx,double cy){
		
		double x,y;
		
		gl.glColor3f(red, green, blue);
		// zone 0
		x=(x0+cx)/divideBy ;
		y=(y0+cy)/divideBy;
		//System.out.println("zone 0: x = "+x+" and y = "+y);
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		
		// zone 1
		x=(y0+cx)/divideBy;
		y=(x0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		// zone 2
		x=(-y0+cx)/divideBy ;
		y=(x0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		// zone 3
		x=(-x0+cx)/divideBy;
		y=(y0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		// zone 4
		x=(-x0+cx)/divideBy;
		y=(-y0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		// zone 5
		x=(-y0+cx)/divideBy;
		y=(-x0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		
		// zone 6
		x=(y0+cx)/divideBy;
		y=(-x0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		// zone 7
		x=(x0+cx)/divideBy;
		y=(-y0+cy)/divideBy;
		if(y < noy){
			// no sun
		}else{
			gl.glVertex2d(x, y);
		}
		
		
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