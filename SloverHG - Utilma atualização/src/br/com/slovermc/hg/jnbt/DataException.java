package br.com.slovermc.hg.jnbt;

public class DataException extends Exception
{
  private static final long serialVersionUID = 5806521052111023788L;

  public DataException(String msg)
  {
    super(msg);
  }

  public DataException()
  {
  }
}