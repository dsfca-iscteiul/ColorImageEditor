class Fotografia {
	private ColorImage fotografia;
	private String [] etiquetas;
	static final int MAX_ETIQUETAS=10;
	private int livre;
	
//CONSTRUTOR 1
	public Fotografia(ColorImage img){
		
		if(img == null){
			throw new NullPointerException("Argumentos nao podem ser null!");
		}else{
			this.fotografia = copiaFoto(img);
			this.etiquetas = new String[MAX_ETIQUETAS];
			this.livre = 0;
		}
	}
//CONTRUTOR 2
	public Fotografia(ColorImage img, String[]etiquetas){
		
		if(img == null || etiquetas.equals(null)){
			throw new NullPointerException("Argumentos nao podem ser null!");
		}else{
			this.fotografia = copiaFoto(img);
			if(etiquetas.length>MAX_ETIQUETAS){
				this.etiquetas = copiaVetor(concatEtiquetas(etiquetas));
				this.livre = MAX_ETIQUETAS;
			}else{
				this.etiquetas = copiaVetor(etiquetas);
			}
		}
	}
//AUXILIARES	
	private String[] concatEtiquetas(String [] etiquetas){
		String [] aux = new String [MAX_ETIQUETAS];
		for(int i=0; i<MAX_ETIQUETAS; i++){
			aux[i] = etiquetas[i];
		}
		return aux;
	}

//CRIAR COPIAS
	private ColorImage copiaFoto(ColorImage img){
		ColorImage aux = new ColorImage(img.getWidth(), img.getHeight());
		
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				aux.setColor(x,y,img.getColor(x,y));
			}
		}
		return aux;
	}
	private String[] copiaVetor(String [] v){
		String [] aux = new String [MAX_ETIQUETAS]; 
		int posicoes = 0;
		for(int x=0; x<v.length && v[x]!=null; x++){
			aux[x] = new String(v[x]);
			posicoes = x+1;
		}
		this.livre = posicoes;
		return aux;
	}
	
	
	
	/**FUNCOES AUXILIARES
	 	-concatEtiquetas(etiquetas)
	 	-copiaFoto(img)
	 	-copiaVetor(vetor)
	 	-grtNrEtiqueta()
	 	-incrLivre()
	 	-getVetorEtiquetas()
	**/
	
//PONTO 1
	//Adicionar uma nova etiqueta textual

	public void addEtiqueta(String etiqueta){
		if(livre == MAX_ETIQUETAS){ 
			throw new IllegalStateException("Impossivel adicionar etiqueta:Tamanho maximo atingido");
		}else if(existeEtiqueta(etiqueta)){
			throw new IllegalArgumentException("Argumento invalido: Etiqueta ja existe");
		}else{
			etiquetas[livre] = etiqueta;
			incrLivre();
		}
	}

	public int getNrEtiquetas(){
		if(etiquetas == null)
			return 0;
		return livre;
	}
	
	private void incrLivre(){
		livre++;
	}
	
//PONTO 2
	//saber se a fotografia esta etiquetada com uma dada etiqueta
	
	public boolean existeEtiqueta(String etiqueta){
		if(etiquetas!=null){
			for(int x=0; x<livre; x++){
				if((etiquetas[x].toLowerCase()).equals(etiqueta.toLowerCase())){
					return true;
				}
			}
		}
		return false;
	} 
	
//PONTO 3
	//obter a imagem da fotografia;
	
	public ColorImage getImagem(){
		return copiaFoto(this.fotografia);
	}
	
//PONTO 4
	//obter uma cadeia de caracteres com todas as etiquetas textuais.
	
	public String getEtiquetas(){
		String s = "";
		for(int x=0; x<livre; x++){
			if(x == MAX_ETIQUETAS-1 || x==livre-1)
				s = s+etiquetas[x];
			else
				s = s+etiquetas[x]+" - ";
		}
		return s;
	}
	
	public String[] getVetorEtiquetas(){
		return this.etiquetas;	
	}
	
//AUXILIAR ORGANIZADOR
	public void alterarFoto(ColorImage c){
		for(int x=0; x<c.getWidth(); x++){
			for(int y=0; y<c.getHeight(); y++){
				fotografia.setColor(x,y,c.getColor(x,y));
			}
		}
	}
				
//--------MAIN--------
	public static void main(String[]args){
		ColorImage img = new ColorImage ("Giraffe.png");
		String [] etiquetas = {"zoo","animal","amarelo","castanho","savetheplanet","LINDA","zooo", "oito", "nove", "dez", "onze"};
		Fotografia f2 = new Fotografia(img, etiquetas);
		f2.getEtiquetas();
		System.out.println("ola");
	}
}