package visor;

import api.ExchangeAPI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import Conversor.Converter.Moeda;








public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean botaoclicked = false;
	private JTextField Txtf1;
	private JTextField Txtf2;
	private JComboBox<String> cb1;
	private JComboBox<String> cb2;
	
	
	
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//String valor = Txta1.getText();
		
		Txtf1 = new JTextField();
		Txtf1.setBounds(118, 86, 242, 23);
		contentPane.add(Txtf1);
		Txtf1.setColumns(10);
		
		Txtf2 = new JTextField();
		Txtf2.setColumns(10);
		Txtf2.setBounds(118, 208, 242, 23);
		contentPane.add(Txtf2);
		Txtf2.setEditable(false);

		JComboBox<Moeda> cb1 = new JComboBox<>();
		cb1.setBounds(118, 57, 136, 22);
		contentPane.add(cb1);
		cb1.addItem(new Moeda("Dólar", "USD" ));
		cb1.addItem(new Moeda("Real", "BRL"));
		cb1.addItem(new Moeda("Euro", "EUR"));
		cb1.addItem(new Moeda("Peso Argentino", "ARS"));
		
		
		
		JComboBox<Moeda> cb2 = new JComboBox<>();
		cb2.setBounds(118, 175, 136, 22);
		contentPane.add(cb2);
		cb2.addItem(new Moeda("Dólar", "USD" ));
		cb2.addItem(new Moeda("Real", "BRL"));
		cb2.addItem(new Moeda("Euro", "EUR"));
		cb2.addItem(new Moeda("Peso Argentino", "ARS"));
		
		
		
		
		JButton btn1 = new JButton("Converter");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
			
				if(!botaoclicked) {
					try {
					 
						Double valor = Double.parseDouble(Txtf1.getText());
						
						Moeda MoedaSelecionar1 = (Moeda) cb1.getSelectedItem();
						Moeda MoedaSelecionar2 = (Moeda) cb2.getSelectedItem();
						
						String Moeda1 = MoedaSelecionar1.MoedaValor();
						String Moeda2 = MoedaSelecionar2.MoedaValor();
						
						//converte o valor que o usuario te pedindo
						
						//A API tem como padrão o USD (Dólar)
						
						 	Double exchangedeUSD = ExchangeAPI.getExchangeRate("USD", Moeda1);

					        Double exchangeparaUSD = ExchangeAPI.getExchangeRate("USD", Moeda2);

					        if (exchangedeUSD == null || exchangeparaUSD == null) {
					            JOptionPane.showMessageDialog(null, "Erro ao obter taxa de câmbio!", "Erro", JOptionPane.ERROR_MESSAGE);
					            return;
					        }
					        //Se a moeda de origem (Moeda1) não for USD, então converte o valor inserido para USD usando a taxa de câmbio de Moeda1 para USD. 
					        if (!Moeda1.equals("USD")) {
					            valor = valor / exchangedeUSD; 
					        }

					        Double conversao = valor * exchangeparaUSD;

					        Txtf2.setText(String.valueOf(conversao));
						
					} catch (NumberFormatException ex){
						 JOptionPane.showMessageDialog(null, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					}
					 
				}
					
			
		});
		btn1.setBounds(176, 269, 148, 29);
		contentPane.add(btn1);
		
		
	}
}
