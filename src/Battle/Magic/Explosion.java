package Battle.Magic;

import Battle.Player;
import Calcuration.Unit;

public class Explosion extends Magic {
	private long mana = 70L;
	
	public long getMana()
	{
		return mana;
	}
	
	public String magicMethod(Player p1, Player p2)
	{
		MagicUtils mu = new MagicUtils();
		Unit u = new Unit();
		
		String process = p1.getName() + " ���� ��� : ����(" + mana + "MP ���)\n";
		try { p1.setMP(mana, Player.SUBSTRACT); } catch(Exception e) {}
		
		long p1Percent = p1.getMAGIC_LEVEL() == 1? 20L : p1.getMAGIC_LEVEL() == 2? 30L : 40L;
		long p2Percent = p1.getMAGIC_LEVEL() == 1? 20L : p1.getMAGIC_LEVEL() == 2? 40L : 50L;
		
		long p1Damage = p1.getHP() * p1Percent / 100;
		long p2Damage = p2.getHP() * p2Percent / 100;
		
		try
		{
			p1.setHP(u.convert(p1Damage), Player.SUBSTRACT);
			p2.setHP(u.convert(p2Damage), Player.SUBSTRACT);
		}
		catch(Exception e) {}
		
		process += p1.getName() + "�� HP�� " + p2.getName() + "�� HP�� ���� " + u.getUnit(p1Damage) + ", " + u.getUnit(p2Damage)
				+ "��ŭ �پ�����.\n";
		mu.setLimitTurn(p1, 1);
		
		return process;
	}
}
