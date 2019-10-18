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

public class line {

	
	static void drawPoint(GL2 gl,int zone, double x0, double y0,double x1, double y1){
		gl.glBegin(GL2.GL_POINTS);
		
		double dx = x1-x0;
		double dy = y1-y0;
		if(x0 <= x1){
			//x0 leftmost
			double x;
			double y;
			double p = 2*dy-dx;
			double p1 = 2*dy;
			double p2 = 2*dy - 2*dx;
			while(x0 < x1){
				if(p < 0){
					//de
					p += p1;
					x0 += 0.001;
				}else{
					//dne
					p += p2;
					x0 += 0.001;
					y0 += 0.001;
				}
				
				if(zone == 0){
					x=x0;y=y0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 1){
					x=y0;y=x0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 2){
					x=-y0;y=x0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 3){
					x=-x0;y=y0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 4){
					x=-x0;y=-y0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 5){
					x=-y0;y=-x0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 6){
					x=y0;y=-x0;
					gl.glVertex2d(x, y);
					
				}else if(zone == 7){
					x=x0;y=-y0;
					gl.glVertex2d(x, y);
				}
			}
		}else{
			//x1 leftmost
			double x;
			double y;
			double p = 2*dy-dx;
			double p1 = 2*dy;
			double p2 = 2*dy - 2*dx;
			while(x1 < x0){
				if(p < 0){
					//de
					p += p1;
					x1 += 0.001;
				}else{
					//dne
					p += p2;
					x1 += 0.001;
					y1 += 0.001;
				}
				if(zone == 0){
					x=x1;y=y1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 1){
					
					x=y1;y=x1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 2){
					x=-y1;y=x1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 3){
					x=-x1;y=y1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 4){
					x=-x1;y=-y1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 5){
					x=-y1;y=-x1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 6){
					x=y1;y=-x1;
					gl.glVertex2d(x, y);
					
				}else if(zone == 7){
					x=x1;y=-y1;
					gl.glVertex2d(x, y);
				}
			}
		}
		
		gl.glEnd();
	}
	
	static void drawLine(GL2 gl, double x0, double y0, double x1, double y1) {
		try {
			
			double dx = x1 - x0;
			double dy = y1 - y0;
			double m;
			double zone0x0;
			double zone0y0;
			double zone0x1;
			double zone0y1;
			
			if(Math.abs(dx) >= Math.abs(dy)){
				//zone 0,3,4,7
				if(dx >= 0 && dy >= 0){
					//zone 0
					
					gl.glVertex2d(x0, y0);
					zone0x0 = x0;
					zone0y0 = y0;
					zone0x1 = x1;
					zone0y1 = y1;
					drawPoint(gl,0,zone0x0,zone0y0,zone0x1,zone0y1);
					gl.glVertex2d(x1, y1);
					
				}else if(dx < 0 && dy >= 0){
					//zone 3
					zone0x0 = -x0;
					zone0y0 = y0;
					zone0x1 = -x1;
					zone0y1 = y1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,3,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
				}else if(dx < 0 && dy < 0){
					//zone 4
					zone0x0 = -x0;
					zone0y0 = -y0;
					zone0x1 = -x1;
					zone0y1 = -y1;
					gl.glVertex2d(x0, y0);
					
					drawPoint(gl,4,zone0x0,zone0y0,zone0x1,zone0y1);
					gl.glVertex2d(x1, y1);
					
					
				}else if(dx >= 0 && dy < 0){
					//zone 7
					zone0x0 = x0;
					zone0y0 = -y0;
					zone0x1 = x1;
					zone0y1 = -y1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,7,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
				}
				
			}else{
				//zone 1,2,5,6
				if(dx >= 0 && dy >= 0){
					//zone 1
					zone0x0 = y0;
					zone0y0 = x0;
					zone0x1 = y1;
					zone0y1 = x1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,1,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
				}else if(dx < 0 && dy >= 0){
					//zone 2
					zone0x0 = y0;
					zone0y0 = -x0;
					zone0x1 = y1;
					zone0y1 = -x1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,2,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
					
				}else if(dx < 0 && dy < 0){
					//zone 5
					zone0x0 = -y0;
					zone0y0 = -x0;
					zone0x1 = -y1;
					zone0y1 = -x1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,5,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
					
				}else if(dx >= 0 && dy < 0){
					//zone 6
					zone0x0 = -y0;
					zone0y0 = x0;
					zone0x1 = -y1;
					zone0y1 = x1;
					gl.glVertex2d(x0, y0);
					drawPoint(gl,6,zone0x0,zone0y0,zone0x1,zone0y1);
					
					gl.glVertex2d(x1, y1);
					
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	
	// end of main
}// end of classimport javax.media.opengl.GL2;