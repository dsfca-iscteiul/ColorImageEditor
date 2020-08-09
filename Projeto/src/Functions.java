class Functions {
	
//LUMINANCE BLUR	
	static void luminanceBlur(ColorImage img, int raio){
		ColorImage aux = new ColorImage (img.getWidth(), img.getHeight());
		
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				Color c = corMedia(img, x, y, raio);
				aux.setColor(x, y, c);
			}
		}
		alterarImg(img, aux);
	}
			
	static Color corMedia(ColorImage img, int x, int y, int raio){
		int inic_x = validacaoRaio(x, raio);
		int inic_y = validacaoRaio(y, raio);
		int luminance = 0;
		int counter = 0;
		for(int a=inic_x; a<img.getWidth() && a<=x+raio; a++){
			for(int b=inic_y; b<img.getHeight() && b<=y+raio; b++){
				luminance = luminance + img.getColor(a,b).getLuminance();
				counter++;
			}
		}
		luminance = validacaoColor(((int)luminance/counter));
		Color c = new Color(luminance, luminance, luminance);
		return c;
	}
			
	static int validacaoRaio(int s, int raio){
		if(s-raio<0)
			return 0;
		return s-raio;
	}
	static int validacaoColor(int color){
		if(color>255)
			return 255;
		else if(color<0)
			return 0;
		return color;
	}
	
//MULTIPLE
	static void multiple(ColorImage img){
		ColorImage aux = new ColorImage(img.getWidth(), img.getHeight());
		int x1=img.getWidth()/2;
		int y1=img.getHeight()/2;
		
		for(int x=0; x<x1; x++){
			for(int y=0; y<y1; y++){
				Color c = corBlocoQuatro(img,x,y);
				aux.setColor(x,y,c);
				aux.setColor(x1+x,y,c);
				aux.setColor(x,y1+y,c);
				aux.setColor(x1+x,y1+y,c);
			}
		}
		alterarImg(img,aux);
	}
					
	static Color corBlocoQuatro(ColorImage img, int x, int y){
		int rd=0;
		int gr=0;
		int bl=0;
		int counter = 0;
		for(int a=x+x; a<img.getWidth() && a<x+x+2; a++){
			for(int b=y+y; b<img.getHeight() && b<y+y+2; b++){
				rd=rd+img.getColor(a,b).getR();
				gr=gr+img.getColor(a,b).getG();
				bl=bl+img.getColor(a,b).getB();
				counter++;
			}
		} 
		rd = rd/counter;
		gr = gr/counter;
		bl = bl/counter;
		Color c = new Color(rd, gr, bl);
		return c;
	}
	
//SATURATE
	
	static void saturate(ColorImage img, double s){
		
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				double p=Math.sqrt((Math.pow(img.getColor(x,y).getR(),2)*0.299)+
								(Math.pow(img.getColor(x,y).getG(),2)*0.587)+
								(Math.pow(img.getColor(x,y).getB(),2)*0.114));
				Color c = new Color(validacaoColor((int)(p+(img.getColor(x,y).getR()-p)*s)),
									validacaoColor((int)(p+(img.getColor(x,y).getG()-p)*s)),
									validacaoColor((int)(p+(img.getColor(x,y).getB()-p)*s)));
				img.setColor(x,y,c);
			}
		}
	}
		
	static void alterarImg(ColorImage img, ColorImage aux){
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				img.setColor(x,y,aux.getColor(x,y));
			}
		}
	}
		
//--------MAIN--------
	public static void main(String[]args){
		ColorImage img = new ColorImage ("aviao.jpg");
		saturate(img, 2);
		System.out.println("ola");
	}

		
}