package Battle.Action;

import javax.crypto.CipherInputStream;

import Battle.Player;
import Calcuration.Unit;

public class Attack extends MyAction {
	@Override
	public String execute(Player p1, Player p2)
	{
		String process = "";
		Unit u = new Unit();
		boolean criticalHit = false;
		
		long attack = u.convert(p1.getATK());
		
		if(p1.getPP() >= 20)
		{
			criticalHit = true;
		}
		
		if(criticalHit)
		{
			process += "ġ��Ÿ! "; 
			attack = u.convert((long)(attack * (p1.getCRITICAL_HIT() / 10))); 
		}
		process += p1.getName() + " ���� : ";
		
		try
		{
			if(p2.getUsingDef() == "defaultDEF")
			{
				long damage = attack - p2.getDefaultDEF();
				if(damage <= 0L) { damage = 0; process+=p1.getName() + "��(��) �ƹ��� �������� ���� ���Ͽ���.\n";}
				else
				{
					p2.setHP(damage, Player.SUBSTRACT);
					process += p1.getName() + " ���ݷ�(" + u.getUnit(attack) + ") - " +
							p2.getName() + " �⺻ ���� (" + u.getUnit(p2.getDefaultDEF()) + ") ="
						   + u.getUnit(damage) + "�� �������� " + p2.getName() + "���� �־���.\n";
				}
				
				p2.setInitHP(p2.getHP());
			}
			else if(p2.getUsingDef() == "DEF")
			{
				if(attack >= p2.getDEF())
				{
					long damage = attack - p2.getDEF();
					long tempdef = p2.getDEF();
					p2.setATKandDEF(p2.getATK(), 0);
					p2.setHP(damage, Player.SUBSTRACT);
					p2.switchUsingDef();
					
					process += p1.getName() + "��(��) " + p2.getName() + "�� ������ 0���� �������, "
							+ p1.getName() + " ���ݷ�(" + u.getUnit(attack) + ") - " + p2.getName()
							+ " ����(" + u.getUnit(tempdef) + ") = " + u.getUnit(damage) + "�� �������� " + p2.getName()
							+ "���� �־���. (" + p2.getName() + " ���� ü�� : " + u.getUnit(p2.getHP()) + ")\n";
					
					if(p2.getHP() != 0)
						process += p2.getName() + "��(��) ������ �⺻ ����(" + u.getUnit(p2.getDefaultDEF()) + ")��"
								+ "�������� ����Ѵ�.\n";
					
					p2.setInitHP(p2.getHP());
					p2.setInitDEF(0L);
				}
				else
				{
					long remaining = p2.getDEF() - attack;
					long tempdef = p2.getDEF();
					p2.setATKandDEF(p2.getATK(), remaining);
					
					process += p2.getName() + "�� ����(" + u.getUnit(tempdef) +")�� " + p1.getName() 
							+ "�� ���ݷ�(" + u.getUnit(attack) + ")��ŭ ��� " + u.getUnit(remaining) + "���� �������.\n";
					p2.setInitDEF(p2.getDEF());
				}
			}
			else
			{
				long damage = u.convert(attack);
				
				p2.setHP(damage, Player.SUBSTRACT);
				p2.setInitHP(p2.getHP());
				
				process += p1.getName() + "��(��) " + p2.getName() + "�� ������ �����ϰ� �����Ͽ���.\n";
				process += p2.getName() + "�� HP�� " + u.getUnit(damage) + "��ŭ ��Ҵ�.\n";
			}
			
			
		}
		
		catch(Exception e)
		{
			
		}
		
		try {
			p1.setPP(10L, Player.ADD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return process;
	}
}
