package Battle.Magic;

import Battle.Player;
import Calcuration.Unit;

public class LifeDrain extends Magic {
	private long mana = 50L;
	
	public long getMana()
	{
		return mana;
	}
	
	public String magicMethod(Player p1, Player p2)
	{
		Unit u = new Unit();
		
		String process = p1.getName() + " ���� ��� : ������ �巹��(" + mana + "MP ���)\n";
		try{ p1.setMP(mana, Player.SUBSTRACT); } catch(Exception e) {}
		
		long percent = p1.getMAGIC_LEVEL() == 1? 10L: p1.getMAGIC_LEVEL() == 2? 15L: 20L;
		long amount = u.convert(p2.getHP() * percent / 100);
		
		try
		{
			p1.setHP(amount, Player.ADD);
			p2.setHP(amount, Player.SUBSTRACT);			
		} catch(Exception e) {}
		
		process += u.getUnit(p2.getHP()) + "�� ü�� �� " + u.getUnit(amount) + "��ŭ�� " + p1.getName()
				+ "�� ü�¿� ������״�.\n";
		return process;
	}
}
