
class Organizador {
	private Fotografia [] colecao;
	private Fotografia exibicao;
	private int livre = 0;
	static final int MAX_FOTOGRAFIAS=10;
	
//PONTO 1
	public Organizador(Fotografia f){
		if(f.equals(null)){
			throw new NullPointerException("Argumentos nao podem ser null!");
		}else{
			this.colecao = inicializarColecao();
			exibicao = null;
			addFotoExibicao(f);
		}
	}
	
//PONTO 2
	public Organizador(ColorImage[]v, String etiqueta){
		this.colecao = inicializarColecao();
		exibicao = null;
		 
		if(!v.equals(null) && !(etiqueta==null)){
			for(int x=0; x<v.length && !(v[x].equals(null)) && x<MAX_FOTOGRAFIAS; x++){
				String [] aux = {etiqueta};
				Fotografia f = new Fotografia(v[x], aux);
				addFotoFim(f);
				if(x==0){
					exibicao = colecao[0];
				}
			}
		}else{
			throw new IllegalArgumentException("Argumentos invalidos!");
		}
	}
	
	private Fotografia[] inicializarColecao(){
		Fotografia[] aux = new Fotografia[MAX_FOTOGRAFIAS];
		return aux;
	}

	/**FUNCOES AUXILIARES
 	-inicializarColecao()
 	-incrLivre(int valor)
 	-removerExibicaoAnterior(int e)
 	-removerExibicaoSeguinte(int e)
 	 	
 	-copiaFoto(img) - tambem na classe Fotografia
 	-quantasFotos(String e)
***/
	
	private void incrLivre(int valor){
		livre = livre+valor;
	}
	
/**	
	public void addFotoFim(Fotografia f){
		if(livre==MAX_FOTOGRAFIAS){
			throw new IllegalStateException("Impossivel adicionar Fotografia:Tamanho maximo atingido");
		}else{
			colecao[livre]=f;
			incrLivre(1);
		}
	}
	
	public void addFotoExibicao(Fotografia f){
		if(livre==MAX_FOTOGRAFIAS){
			throw new IllegalStateException("Impossivel adicionar Fotografia:Tamanho maximo atingido");
		}else{
			if(livre!=0){
				for(int x=livre; x>0; x--){
					colecao[x]=colecao[x-1];
				}
			}
			colecao[0]=f;
			exibicao = colecao[0];
			incrLivre(1);
		}
	}**/
	
//PONTO 3
	//Adicionar uma fotografia, dado um objeto fotografia. Este objeto
	//é adicionado no fim da coleção de fotgrafias;
	
	public void addFotoFim(Fotografia f){
		if(livre==MAX_FOTOGRAFIAS){
			throw new IllegalStateException("Impossivel adicionar Fotografia:Tamanho maximo atingido");
		}else{
			colecao[livre]=new Fotografia(f.getImagem(), f.getVetorEtiquetas());
			if(livre==0){
				exibicao=colecao[livre];
			}	
			incrLivre(1);
		}
	}
	
//PONTO 4
	//Inserir uma fotografia, dado um objeto fotografia. Este objeto
	//é inserido na posicao da imagem em exibicao, provocando o deslocamento 
	//das todas as fotografias a partir da que estava em exibicao (inclusive).
	
	public void addFotoExibicao(Fotografia f){
		if(livre==MAX_FOTOGRAFIAS){
			throw new IllegalStateException("Impossivel adicionar Fotografia:Tamanho maximo atingido");
		}else{
			if(livre!=0){
				for(int x=livre; x>0; x--){
					colecao[x]=new Fotografia(colecao[x-1].getImagem(), colecao[x-1].getVetorEtiquetas());
				}
			}
			colecao[0]=new Fotografia(f.getImagem(), f.getVetorEtiquetas());
			exibicao = colecao[0];
			incrLivre(1);
		}
	}
	
//PONTO 5
	//Remover a fotografia em exibicao
	
	public void removerExibicao(){
		if(!(livre==0)){
			for(int x=0; x<livre; x++){
				
				if(colecao[x].equals(exibicao)){
					if(x+1==MAX_FOTOGRAFIAS || x+1==livre){  //TALVEZ PROBLEM
						removerExibicaoAnterior(x);
					}else{
						removerExibicaoSeguinte(x);
					}
					incrLivre(-1);
				}
			}
		}else{
			throw new IllegalStateException("Erro: Nao ha nada para remover!");
		}
	}
	
	private void removerExibicaoAnterior(int e){
		if(livre==1){
			colecao[e]=null;
			exibicao=null;
		}else{
			exibicao = colecao[e-1];
			colecao[e] = null;
		}
	}
	
	private void removerExibicaoSeguinte(int e){
		for(int x=e; x<livre; x++){
			if(x+1==livre){
				colecao[x]=null;
			}else{
				colecao[x] = colecao[x+1];//new Fotografia(colecao[x+1].getImagem(), colecao[x+1].getVetorEtiquetas());
			}
		}
		exibicao = colecao[e];
	}
	
//PONTO 6
	//obter a imagem em exibicao
	public ColorImage obterImagemExibicao(){
		return exibicao.getImagem();
	}
	
//PONTO 9
	//avancar foto exibicao
	public void avancarExibicao(){
		if(livre>1){
			for(int x=0;x<livre;x++){
				if(colecao[x].equals(exibicao) && x+1==livre){
					throw new IllegalStateException("Nao existem fotografias seguintes na colecao!");
				}else if(colecao[x].equals(exibicao) && x+1!=livre){
					exibicao=null;
					exibicao=colecao[x+1];
					return;
				}
			}
		}else{
			throw new IllegalStateException("Nao existem fotografias seguintes na colecao!");
		}			
	}
//PONTO 10
	//recuar exibicao
	public void recuarExibicao(){
		for(int x=0; x<livre; x++){
			if(colecao[x].equals(exibicao)){
				if(x-1!=-1){
					exibicao=colecao[x-1];
				}else{
					throw new IllegalStateException("Nao existem Fotografias anteriores na colecao!");
				}
			}
		}
	}
//PONTO 7
	//obter a anterior à exibicao com luminance blur
	public ColorImage efeitoLuminance_Anterior(int raio_do_efeito){
		ColorImage img;
		if(livre>1){
			for(int x=0; x<livre; x++){
				if(colecao[x].equals(exibicao) && x!=0){
					img = copiaFoto(colecao[x-1].getImagem());
					Functions.luminanceBlur(img,raio_do_efeito);
					return img;
				}
			}
		}
		System.out.println("Impossivel nao existe imagem anterior à exibicao!");
		return null;
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
//PONTO 8
	public ColorImage efeitoLuminance_Seguinte(int raio_do_efeito){
		ColorImage img;
		if(livre>1){
			for(int x=0; x<livre; x++){
				if(colecao[x].equals(exibicao) && x+1!=livre){
					img = copiaFoto(colecao[x+1].getImagem());
					Functions.luminanceBlur(img,raio_do_efeito);
					return img;
				}
			}
		}
		System.out.println("Impossivel nao existe imagem seguinte à exibicao!");
		return null;
	}
	
//PONTO 11
	//Obter um vetor com as ColorImage que têm uma dada etiqueta textual
	public ColorImage[] imagensQueTemEtiqueta_x(String etiqueta){
		ColorImage[]vetor = new ColorImage[quantasFotos(etiqueta)];
		int proximo=0;
		for(int x=0; x<livre; x++){
			if(colecao[x].existeEtiqueta(etiqueta)){
				vetor[proximo] = colecao[x].getImagem();
				proximo++;
			}
		}
		return vetor;
	}
		
	private int quantasFotos(String etiqueta){
		int ocorrencias = 0;
		for(int x=0; x<livre;x++){
			if(colecao[x].existeEtiqueta(etiqueta)){
				ocorrencias++;
			}
		}
		return ocorrencias;
	}
	
//PONTO 12
	//obter exibicao com multiple
	public ColorImage efeitoMultiple_Exibicao(){
		ColorImage img;
		if(livre!=0){
			for(int x=0; x<livre; x++){
				if(colecao[x].equals(exibicao)){
					img = copiaFoto(colecao[x].getImagem());
					Functions.multiple(img);
					return img;
				}
			}
		}
		System.out.println("Impossivel nao existe imagem em exibicao!");
		return null;
	}
	
//PONTO 13
	//saturar cores da imagem em exibicao
	public void efeitoSaturar_Exibicao(double nivel_saturacao){
		if(livre!=0){
			for(int x=0; x<livre; x++){
				if(colecao[x].equals(exibicao)){
					ColorImage img=colecao[x].getImagem();
					Functions.saturate(img, nivel_saturacao);
					colecao[x].alterarFoto(img);
					return;
				}
			}
		}
		System.out.println("Impossivel nao existe imagem em exibicao!");
	}
					
//-------MAIN-------
	public static void main(String[]args){
		ColorImage girafa = new ColorImage ("Giraffe.png");
		ColorImage aviao = new ColorImage("aviao.jpg");
		ColorImage [] v = {girafa, aviao};
		String [] etiquetas = {"zoo","animal","amarelo","castanho","savetheplanet"};
		String s = "hello";
		Organizador o = new Organizador(v,s);
//FUNCOES
		Fotografia f = new Fotografia(girafa,etiquetas);
		o.addFotoExibicao(f);
		//f.getNrEtiquetas();
		//f.getEtiquetas();
//
		/*o.removerExibicao();
		o.removerExibicao();
		o.removerExibicao();
		o.addFotoFim(f);
		o.obterImagemExibicao();
		System.out.println("ola");*/
	}
}