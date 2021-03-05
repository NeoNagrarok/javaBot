package BotThread;

import gui.ChatGui;

public class BotThread implements Runnable {

	private String input = "";
	private Parser parser = new Parser();
	private ChatGui chatGui;

	public void setInput(String input) {
		this.input = input;
	}

	public void setChatGui(ChatGui chatGui) {
		this.chatGui = chatGui;
	}

	private void process() {
		System.out.println("Start thread");
		while (true)
		{
			if (!this.input.equals("")) {
				if (!this.input.equals("Conversation nettoyées !")){
					String response = parser.process(this.input);
					System.out.println("Bot : " + response);
					this.chatGui.addStringToGUI("Bot : " + response);
				}
			}
			synchronized (this)
			{
				try
				{
					this.wait();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		this.process();
	}
}
