<%@page import="javax.imageio.ImageIO"%>
<%@ page autoFlush="false"
	import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*"%>
<%@ page import="org.apache.commons.lang3.RandomStringUtils"%>
<%
	String yzm = RandomStringUtils.randomNumeric(4);
%>
<%!Color getRandColor(int fc, int bc) {
		Random random1 = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random1.nextInt(bc - fc);
		int g = fc + random1.nextInt(bc - fc);
		int b = fc + random1.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	out.clear();
	try {
		response.setContentType("image/jpeg");
		response.addHeader("pragma", "NO-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expries", 0);
		int width = 50, height = 16;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Simsun", Font.PLAIN, 15));
		g.drawString(yzm, 2, 12);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());        
	} catch (Exception e) {
	}
%>
<%
	session.setAttribute("yzm", yzm);

%>

