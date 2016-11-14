package Battle.Magic;

import Battle.Player;
import Calcuration.Unit;

public class Curse extends Magic {
	private long mana = 40L;
	
	public long getMana()
	{
		return mana;
	}
	
	public String magicMethod(Player p1, Player p2)
	{
		Unit u = new Unit();
		String process = p1.getName() + " ���� ��� : ����(" + mana + "MP ���)\n";

		try
		{
			p1.setMP(mana, Player.SUBSTRACT);
		} catch(Exception ex) {}
		
		long MPamount =  p2.getMP() * (p1.getMAGIC_LEVEL() == 1? 30 : p1.getMAGIC_LEVEL() == 2? 40: 50) / 100;
		long ATKamount = p2.getATK() * 20 / 100;
		long DEFamount = (p2.getUsingDef() == "defaultDEF"? p2.getDefaultDEF() : p2.getDEF()) * 20 / 100;
		
		long DEF = p2.getUsingDef() == "DEF"? p2.getDEF() : p2.getDefaultDEF();
		
		try
		{
			p2.setMP(MPamount, Player.SUBSTRACT);
			p2.setATKandDEF(u.convert(p2.getATK() - ATKamount), u.convert(DEF - DEFamount));
		}
		catch(Exception e) {}
		
		process += p2.getName() + "�� MP�� " + MPamount + ", ���ݷ��� " + u.getUnit(ATKamount) + ", ������ "
				+ u.getUnit(DEFamount) + "��ŭ�� ���ҽ��״�. ���ݷ°� ������ ���� " + p1.getName() +"�� �Ͽ� ������� ���ƿ´�.\n";
		return process;
	}
}
