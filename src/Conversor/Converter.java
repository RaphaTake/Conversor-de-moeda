package Conversor;


public class Converter {

	public static void main(String[] args) {
	}
		public static class Moeda{
			private String MoedaView;
			private String MoedaReal;
			
			public Moeda(String MoedaView, String MoedaReal) {
				this.MoedaReal = MoedaReal;
				this.MoedaView = MoedaView;
			}
			
			public String MoedaValor() {
				return MoedaReal;
			}
			
			public String MoedaOlhar() {
				return MoedaView;
			}
			public String toString(){
				return MoedaView;
			}
			
		}
		
		


	}


