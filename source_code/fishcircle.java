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

public class fishcircle {

	// static double z4lx = 0;
	// static double z4ly = 0;
	//
	// static double z3lx = 0;
	// static double z3ly = 0;

	//static double noy = -.62; // below this no fish

	static int divideBy = 600;
	
	void drawCircle(GL2 gl, double cx, double cy, double r, boolean flag, int position, int fishcolor) {
		// flag = true means upper portion
		// flag = false means lower portion

		// position 1 => upward fish
		// position 2 => fish
		// position 3 => downward fish

		double x = r;
		double y = 0;

		drawPoint(gl, x, y, cx, cy, flag, position,fishcolor);
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

			drawPoint(gl, x, y, cx, cy, flag, position,fishcolor);
		}
	}

	void drawPoint(GL2 gl, double x0, double y0, double cx, double cy, boolean flag, int position, int fishcolor) {
		// flag = true means upper portion
		// flag = false means lower portion

		// position 1 => upward fish
		// position 2 => fish
		// position 3 => downward fish
		
		// front fish color = 1f, 0f, 1f ==> 1
		// middle fish color = .5f, 1f, .5f ==> 2
		// last fish color = 1f, .5f, .75f ==> 3

		gl.glBegin(GL2.GL_POINTS);
		double x, y;
		if (position == 1) {
			// upward fish
			
			if(fishcolor == 1){
				gl.glColor3f(1f, 0f, 1f);
				
			}else if(fishcolor == 2){
				gl.glColor3f(.5f, 1f, .5f);
				
			}else if(fishcolor == 3){
				gl.glColor3f(1f, .5f, .75f);
				
			}
			
			if (flag) {
				// upper part

				// zone 2
				x = (-y0 + cx) / divideBy;
				y = (x0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 3
				x = (-x0 + cx) / divideBy;
				y = (y0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 4
				x = (-x0 + cx) / divideBy;
				y = (-y0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

			} else {
				// lower part

				// zone 6
				x = (y0 + cx) / divideBy;
				y = (-x0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);

				// zone 7
				x = (x0 + cx) / divideBy;
				y = (-y0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);

				// zone 5
				x = (-y0 + cx) / divideBy;
				y = (-x0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);

			}

		} else if (position == 2) {
			// up fish
			if(fishcolor == 1){
				gl.glColor3f(1f, 0f, 1f);
				
			}else if(fishcolor == 2){
				gl.glColor3f(.5f, 1f, .5f);
				
			}else if(fishcolor == 3){
				gl.glColor3f(1f, .5f, .75f);
				
			}

			if (flag) {
				// upper part
				// zone 1
				x = (y0 + cx) / divideBy;
				y = (x0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 2
				x = (-y0 + cx) / divideBy;
				y = (x0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 3
				x = (-x0 + cx) / divideBy;
				y = (y0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

			} else {
				// lower part
				// zone 6
				x = (y0 + cx) / divideBy;
				y = (-x0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
				// zone 5
				x = (-y0 + cx) / divideBy;
				y = (-x0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
				// zone 4
				x = (-x0 + cx) / divideBy;
				y = (-y0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
			}

		} else if (position == 3) {
			// downward fish
			if(fishcolor == 1){
				gl.glColor3f(1f, 0f, 1f);
				
			}else if(fishcolor == 2){
				gl.glColor3f(.5f, 1f, .5f);
				
			}else if(fishcolor == 3){
				gl.glColor3f(1f, .5f, .75f);
				
			}
			
			if (flag) {
				// upper part

				// zone 0
				x = (x0 + cx) / divideBy;
				y = (y0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 1
				x = (y0 + cx) / divideBy;
				y = (x0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

				// zone 2
				x = (-y0 + cx) / divideBy;
				y = (x0 + cy) / divideBy;
				Anime.ullx.add(x);
				Anime.ully.add(y);
				gl.glVertex2d(x, y);

			} else {
				// lower part
				// zone 5
				x = (-y0 + cx) / divideBy;
				y = (-x0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
				// zone 4
				x = (-x0 + cx) / divideBy;
				y = (-y0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
				// zone 3
				x = (-x0 + cx) / divideBy;
				y = (y0 + cy) / divideBy;
				Anime.lllx.add(x);
				Anime.llly.add(y);
				gl.glVertex2d(x, y);
				
			}
		}

		gl.glEnd();
	}
	// end of main
}// end of classimport javax.media.opengl.GL2;