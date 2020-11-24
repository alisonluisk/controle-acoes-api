package com.ibolsa.api.helper;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Slf4j
public class DateHelper {

	/** {@code String} com o formato padrao de data: <b>dd/MM/yyyy</b> */
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	/** {@code String} com o formato padrao de horas: <b>HH:mm</b> */
	public static final String HOUR_FORMAT = "HH:mm";
	/** {@code String} com o formato padrão de data e hora: <b>dd/MM/yyyy HH:mm:ss</b> */
	public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	private DateHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Obtém o primeiro dia do mês da data informada
	 *
	 * @param data a data de referência
	 * @return a data com o primeiro dia do mês
	 */
	public static Date getPrimeiroDiaMes(Date data) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		retorno.set(Calendar.DAY_OF_MONTH, retorno.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		return retorno.getTime();
	}
	
	/**
	 * Obtém o primeiro dia do mês informado.
	 *
	 * @param mes o mês de referência
	 * @return a data com o primeiro dia do mês informado
	 */
	public static Date getPrimeiroDiaMes(Integer mes) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(new Date());
		retorno.set(Calendar.MONTH, mes);
		retorno.set(Calendar.DAY_OF_MONTH, retorno.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		return retorno.getTime();
	}
	
	public static Date getSetimoDia(Integer mes) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(new Date());
		retorno.set(Calendar.MONTH, mes);
		retorno.set(Calendar.DAY_OF_MONTH,7);
		
		return retorno.getTime();
	}
	
	
	public static Date getUltimoDiaMes(Date data) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		retorno.set(Calendar.DAY_OF_MONTH, retorno.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return retorno.getTime();
	}
	
	public static String parseToString(Date data) {
		return new SimpleDateFormat(DATE_FORMAT).format(data);
	}
	
	public static Date parseToDate(String data) throws ParseException {
		return new SimpleDateFormat(DATE_FORMAT).parse(data);
	}
	
	public static Date zerarHoras(Date data){
		if(data == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
	 * Somar x dias na data atual</br>
	 *     </br> <b>Ex: Hoje = 07/11/2016
	 *     </br> Somar 10 dias;
	 *     </br> Return: 27/11/2016
	 *     </b>
	 * @param today = Hoje
	 * @param days = Dias para somar
	 * @return Hoje + X dias;
	 */
	public static Date addDayToday(Date today, Integer days){
		if(today == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + (days == null ? 0 : days));

		return cal.getTime();
	}
	
	public static Date ultimaHoras(Date data){
		if(data == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
	 * Retorna o ano relativo a data informada.
	 * <ul><b>Exemplo:</b> Para 30/06/2016 (data informada) será retornado 2016</ul>
	 * @param data a data que deseja encontrar o ano
	 * @return o ano relativo a data informada, conforme exemplo
	 * @author Filipe Wutzke
	 */
	public static Integer yearToDate(Date data){
		if(data == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Retorna o ano (reduzido) relativo a data informada.
	 * <ul><b>Exemplo:</b> Para 30/06/2016 (data informada) será retornado 16</ul>
	 * @param data a data que deseja encontrar o ano
	 * @return o ano relativo a data informada, conforme exemplo
	 * @author Filipe Wutzke
	 */
	public static Integer yearToDateShort(Date data){
		if(data == null)
			data = new Date(); //NOSONAR
		
		return Integer.valueOf(new SimpleDateFormat("yy").format(data));
	}
	
	/**
	 * Retorna o semestre relativo a data informada.
	 * <ul><b>Exemplo:</b> Para 30/06/2016 (data informada) será retornado 1</ul>
	 * @param data a data que deseja encontrar o semestre
	 * @return o semestre do ano relativo a data informada, conforme exemplo
	 * @author Filipe Wutzke
	 */
	public static Integer semesterToDate(Date data){
		if(data == null)
			data = new Date(); //NOSONAR
		
		return monthToDate(data) <= 6 ? 1 : 2;
	}
	
	/**
	 * Retorna o mês relativo a data informada.
	 * O retorno é relativo ao número do mês, num intervalo de 1-12.
	 * <ul><b>Exemplo:</b> Para 30/06/2016 (data informada) será retornado 6</ul>
	 * 
	 * @param data a data que deseja encontrar o mês
	 * @return o número relativo ao mês, conforme exemplo
	 * @author Filipe Wutzke
	 */
	public static Integer monthToDate(Date data){
		if(data == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(Calendar.MONTH) + 1;
		
	}
	
	/**
	 * Retorna o mês passado.
	 * O retorno é relativo ao número do mês, num intervalo de 1-12.
	 * <ul><b>Exemplo:</b> Para 15/10/2016 (data atual) será retornado 9</ul>
	 *
	 * @return o número relativo ao mês passado, conforme exemplo
	 * @author Filipe Wutzke
	 */
	public static Integer lastMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * Verifica se a data informada é domingo.
	 *
	 * @param data a data de referência
	 * @return {@code true}, se é domingo
	 * @author Filipe Wutzke
	 */
	public static boolean isDomingo(Date data) {
		if(data == null)
			data = new Date(); //NOSONAR
		
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		return retorno.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}
	
	/**
	 * Verifica se a data informada é sábado.
	 *
	 * @param data a data de referência
	 * @return {@code true}, se é sábado
	 * @author Filipe Wutzke
	 */
	public static boolean isSabado(Date data) {
		if(data == null)
			data = new Date(); //NOSONAR
		
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		return retorno.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}
	
	/**
	 * Verifica se o horário da data informada está dentro do intervalo de horário comercial.<p>
	 * O horário comercial é o período de tempo durante o qual os estabelecimentos de comércio e serviços realizam atendimento ao consumidor, 
	 * compreendendo o intervalo de segunda-feira à sexta-feira, das 08h às 18h, desconsiderado horário de almoço e
	 * sábado, das 8h às 12h.
	 *
	 * @param data a data de referência
	 * @return {@code true}, se for horário comercial
	 * @author Filipe Wutzke
	 */
	public static boolean isHorarioComercial(Date data){
		if(data == null)
			data = new Date(); //NOSONAR
		
		if(isDomingo(data))
			return false;
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(data);
		int hora = c1.get(Calendar.HOUR_OF_DAY);
		
		if(isSabado(data) && (hora >= 8 && hora < 12))
			return true;
		
		return hora >= 8 && hora < 18;
	}
	
	/**
	 * Verifica se a data informada coincide com algum feriado nacional.
	 * Os feriados nacionais conhecidos e catalogados são:
	 * <ul>
	 * <li>Confraternização Universal - 01 de Janeiro</li>
	 * <li>Tiradentes - 21 de Abril</li>
	 * <li>Dia do Trabalhador - 01 de Maio</li>
	 * <li>Independência do Brasil - 07 de Setembro</li>
	 * <li>Nossa Senhora Aparecida/Dia das Crianças - 12 de Outubro</li>
	 * <li>Finados - 02 de Novembro</li>
	 * <li>Proclamação da República - 15 de Novembro</li>
	 * <li>Natal - 25 de Dezembro</li>
	 * </ul>
	 * Será validado também os feriados dinâmicos, que cada ano incidem em datas diferentes:
	 * <ul>
	 * <li>Sexta-feria da Paixão</li>
	 * <li>Carnaval</li>
	 * <li>Corpus Christi</li>
	 * </ul>
	 *
	 * @param data a data de referência
	 * @return {@code true}, se a data informada for feriado
	 * @author Filipe Wutzke
	 */
	public static boolean isFeriado(Date data){
		if(data == null)
			data = new Date(); //NOSONAR
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		// 01 de Janeiro - Confraternização Universal
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		if(cal.getTime().equals(data))
			return true;
		
		// 21 de Abril - Tiradentes
		cal.set(Calendar.DAY_OF_MONTH, 21);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		if(cal.getTime().equals(data))
			return true;
		
		// 01 de Maio - Dia do Trabalhador
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.MAY);
		if(cal.getTime().equals(data))
			return true;
		
		// 07 de Setembro - Independência do Brasil
		cal.set(Calendar.DAY_OF_MONTH, 7);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		if(cal.getTime().equals(data))
			return true;
		
		// 12 de Outubro - Nossa Senhora Aparecida / Dia das Crianças
		cal.set(Calendar.DAY_OF_MONTH, 12);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		if(cal.getTime().equals(data))
			return true;
		
		// 02 de Novembro - Finados
		cal.set(Calendar.DAY_OF_MONTH, 2);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		if(cal.getTime().equals(data))
			return true;
		
		// 15 de Novembro - Proclamação da República
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		if(cal.getTime().equals(data))
			return true;
		
		// 25 de Dezembro - Natal
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		if(cal.getTime().equals(data))
			return true;
		
		return isPascoaOrCarnavalOrCorpusChristi(data);
	}
	
	/**
	 * Verifica se a data informada coincide com os feriados <b>Sexta-feria da Paixão</b> ou <b>Carnaval</b> ou <b>Corpus Christi</b>.
	 * Será feito o cálculo para identificar quando será a páscoa, conforme o ano informado. 
	 * Após identificada a páscoa é possível dizer quando será os feriados:
	 * <li>Sexta-feria da Paixão - 2 dias antes da páscoa</li>
	 * <li>Carnaval - 47 dias antes da páscoa</li>
	 * <li>Corpus Christi - 60 dias após a páscoa</li>
	 *
	 * @param data a data de referência
	 * @return {@code true}, se for 'Paixão' ou 'Carnaval' ou 'Corpus Christi'
	 * @author Filipe Wutzke
	 */
	public static boolean isPascoaOrCarnavalOrCorpusChristi(Date data) {
		data = zerarHoras(data);
		int ano = yearToDate(data);

		int  a = ano % 19;
		int b = ano / 100;
		int c = ano % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i =  c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int mes = (h + l - 7 * m + 114) / 31;
		int dia = ((h + l - 7 * m + 114) % 31) + 1;

		//Primeiro encontra a data da páscoa, que será referência para os demais
		GregorianCalendar calendarPascoa = new GregorianCalendar();
		calendarPascoa.set(Calendar.YEAR, ano);
		calendarPascoa.set(Calendar.MONTH, mes-1);
		calendarPascoa.set(Calendar.DAY_OF_MONTH, dia);

		GregorianCalendar calendarFeriados = new GregorianCalendar();
		//Carnaval - 47 dias antes da páscoa
		calendarFeriados.setTimeInMillis(calendarPascoa.getTimeInMillis());
		calendarFeriados.add(Calendar.DAY_OF_MONTH, -47);
		Date dataFeriado = zerarHoras(calendarFeriados.getTime());
		if(dataFeriado != null && dataFeriado.equals(data))
			return true;

		//CorpusChristi - 60 dias após a páscoa
		calendarFeriados.setTimeInMillis(calendarPascoa.getTimeInMillis());
		calendarFeriados.add(Calendar.DAY_OF_MONTH, 60);
		dataFeriado = zerarHoras(calendarFeriados.getTime());
		if(dataFeriado != null && dataFeriado.equals(data))
			return true;

		//Sexta-feria da Paixão - 2 dias antes da páscoa
		calendarFeriados.setTimeInMillis(calendarPascoa.getTimeInMillis());
		calendarFeriados.add(Calendar.DAY_OF_MONTH, -2);
		dataFeriado = zerarHoras(calendarFeriados.getTime());
		
		return dataFeriado != null && dataFeriado.equals(data);
	}
	
	/**
	 * Recebe uma data sem horario e retorna a data com horario atual
	 * @param data Ex: 15/01/2010 00:00:00:000
	 * @return Date Ex: 15/01/2010 15:15:15:999
	 * @author Victor Alves
	 */
	public static Date adicionarHoraAtual(Date data){
		if(data == null)
			return null;
		//Cria um calendar com horario Ex: 15/01/2010 15:15:15:999
		Calendar calHoraAtual = Calendar.getInstance();
		calHoraAtual.setTime(new Date());
		//Cria um calendar com horario passado por parametro sem horario
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		cal.set(Calendar.HOUR_OF_DAY, calHoraAtual.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, calHoraAtual.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, calHoraAtual.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, calHoraAtual.get(Calendar.MILLISECOND));
		
		return cal.getTime();
	}

	/**
	 * Obtém o primeiro dia da semana.
	 * @return a data com o primeiro dia da semana
	 */
	public static Date getPrimeiroDiaSemana(Date data) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		retorno.set(Calendar.DAY_OF_WEEK, retorno.getActualMinimum(Calendar.DAY_OF_WEEK));
		
		return retorno.getTime();
	}
	
	/**
	 * Obtém o ultimo dia da semana.
	 * @return a data com o ultimo dia da semana
	 */
	public static Date getUltimoDiaSemana(Date data) {
		Calendar retorno = new GregorianCalendar();
		retorno.setTime(data);
		retorno.set(Calendar.DAY_OF_WEEK, retorno.getActualMaximum(Calendar.DAY_OF_WEEK));
		
		return retorno.getTime();
	}
	/**
	 * Busca 30 dias após a data passada para o metodo
	 * @param data
	 * @return 30 dias após a data que fui passada
	 */
	public static Date getTrintaDias(Date data){
		Date aposTrintaDias = data;
		Calendar cal = Calendar.getInstance();
		cal.setTime(aposTrintaDias);
		cal.add(Calendar.DATE, 30);
		return aposTrintaDias = cal.getTime();
	}
	
}
