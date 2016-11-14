package Battle.Magic;

import Battle.Player;
import Calcuration.Unit;

public class Bless extends Magic {
	private long mana = 40L;
	
	public long getMana()
	{
		return mana;
	}
	
	public String magicMethod(Player p1, Player p2)
	{
		Unit u = new Unit();
		
		String process = p1.getName() + " ���� ��� : �ູ(" + mana + "MP ���)\n";
		
		try 
		{
			p1.setMP(mana, Player.SUBSTRACT);
		} catch (Exception e) {  }
		
		long MPamount = 0L;
		long ATKamount = 0L;
		long DEFamount = 0L;
		
		MPamount = p1.getMP() * (p1.getMAGIC_LEVEL() == 1? 30 : p1.getMAGIC_LEVEL() == 2? 40 : 50) / 100;	
								// ������ ���Ͽ� �ۼ������� ��� (���� ������ ���)
		
		ATKamount = p1.getATK() * 20 / 100;
		DEFamount = (p1.getUsingDef() == "defaultDEF"? p1.getDefaultDEF() * 20
					: p1.getDEF() * 20) / 100;	// �⺻ ������ ���Ͽ� �ۼ������� ��� (���� ������ ���)
		
		try { p1.setMP(MPamount, Player.ADD); } catch (Exception e) {}
		p1.setATKandDEF(u.convert(p1.getATK() + ATKamount), u.convert(p1.getUsingDef() == "DEF"?p1.getDEF() 
						: p1.getDefaultDEF() + DEFamount));
		
		process += p1.getName() + "�� " + "MP�� " + MPamount + "��ŭ, ���ݷ��� " + u.getUnit(ATKamount)
				+ "��ŭ, ������ " + u.getUnit(DEFamount) + "��ŭ �����Ͽ���. ���ݷ°� ������ ���� " + p1.getName() +"�� �Ͽ� ������� ���ƿ´�.\n";
		
		return process;
	}
}
