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

public class normalcircle {
	
	static int divideBy = 600;
	
	static void fill(GL2 gl, double cx, double cy, double r){
		while(r > 0){
			drawCircle(gl,cx,cy,r);
			r--;
		}
		
	}
	static void drawCircle(GL2 gl, double cx, double cy, double r) {
		
		double x = r;
		double y = 0;

		drawPoint(gl, x, y, cx, cy);
		// double d = 1 - r;
		// double d = 5 - 4*r;
		double d = 5 / 4 - r;

		while (y <= x) {
			if (d < 0) {

				d += (2 * y + 3);
				y++;
			} else {

				d += (2 * y - 2 * x + 5);

				y++;
				x--;
			}

			drawPoint(gl, x, y, cx, cy);
		}
	}

	static void drawPoint(GL2 gl, double x0, double y0, double cx, double cy) {
		
		gl.glBegin(GL2.GL_POINTS);
		if(Anime.eyeorstar){
			gl.glColor3f(0f, 0f, 0f);
		}else{
			gl.glColor3f(1f, 1f, 1f);
		}
		
		double x, y;
		
		// zone 0
		x=(x0+cx) /divideBy; y=(y0+cy)/divideBy;	
		gl.glVertex2d(x, y);
				
				
		// zone 1
		x = (y0 + cx) / divideBy;
		y = (x0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		
		// zone 2
		x = (-y0 + cx) / divideBy;
		y = (x0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		
		// zone 3
		x = (-x0 + cx) / divideBy;
		y = (y0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		
		// zone 4
		x = (-x0 + cx) / divideBy;
		y = (-y0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		
		// zone 5
		x = (-y0 + cx) / divideBy;
		y = (-x0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		
		// zone 6
		x = (y0 + cx) / divideBy;
		y = (-x0 + cy) / divideBy;
		gl.glVertex2d(x, y);

		// zone 7
		x = (x0 + cx) / divideBy;
		y = (-y0 + cy) / divideBy;
		gl.glVertex2d(x, y);
		

		gl.glEnd();
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