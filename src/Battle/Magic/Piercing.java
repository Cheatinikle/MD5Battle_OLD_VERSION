package Battle.Magic;

import Battle.Player;

public class Piercing extends Magic {
	private long mana = 90L;
	
	public long getMana()
	{
		return mana;
	}
	
	public String magicMethod(Player p1, Player p2)
	{
		String process = p1.getName() + " ���� ��� : ����\n";
		
		try
		{
			p1.setMP(mana, Player.SUBSTRACT);
		}
		catch(Exception e) {}
		
		MagicUtils m = new MagicUtils();
		m.ignoreDefence(p1, p2);
		process += p1.getName() + "��(��) �̹� �� " + p2.getName() + "�� ������ �����ϰ� ������ �� �ִ�.\n"; 
		return process;
	}
}
