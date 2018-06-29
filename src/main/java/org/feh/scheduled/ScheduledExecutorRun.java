package org.feh.scheduled;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.feh.model.ScheduledExecutorTask;
import org.feh.service.TimerTaskModelService;
import org.springframework.stereotype.Component;

@Component
public class ScheduledExecutorRun {

	@Resource
	private TimerTaskModelService modelService;

	private Long firstTime;
	private Long period;

	private Logger logger = Logger.getLogger(this.getClass());

	public void init() {
		List<ScheduledExecutorTask> timerTaskModels = modelService.findAllClazzNotNull();
		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(timerTaskModels.size());
		timerTaskModels.forEach(m -> {
			String _clazz = m.getClazz();
			setTaskTime(m);
			if(period != null) {
				scheduled.scheduleWithFixedDelay(()->{
					try {
						Class<?> clazz = Class.forName(_clazz);
						Method _method = clazz.getDeclaredMethod("run");
						_method.invoke(clazz.newInstance());
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
						logger.info("-----------------定时任务【" + _clazz + "】执行失败!-----------------", e);
						e.printStackTrace();
					}
				}, firstTime, period, TimeUnit.SECONDS);
			} else {
				scheduled.schedule(()->{
					try {
						Class<?> clazz = Class.forName(_clazz);
						Method _method = clazz.getDeclaredMethod("run");
						_method.invoke(clazz.newInstance());
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
						logger.info("-----------------定时任务【" + _clazz + "】执行失败!-----------------", e);
					}
				}, firstTime, TimeUnit.SECONDS);
			}
			
		});
	}

	private void setTaskTime(ScheduledExecutorTask task) {
		Integer hour = task.getFirstH();
		Integer min = task.getFirstM();
		Integer sec = task.getFirstS();
		firstTime = 1l;
		if(hour != null && hour != 0) {
			firstTime *= hour * 60 * 60;
		}
		if(min != null && min != 0) {
			firstTime *= min * 60;
		}
		if(sec != null && sec != 0) {
			firstTime *= sec;
		}
		long per = 1l;
		boolean flag = false;
		if(task.getCycleH() != null && task.getCycleH() != 0) {
			per *= task.getCycleH() * 60 * 60;
			flag = true;
		}
		if(task.getCycleM() != null && task.getCycleM() != 0) {
			per *= task.getCycleM() * 60;
			flag = true;
		}
		if(task.getCycleS() != null && task.getCycleS() != 0) {
			per *= task.getCycleS();
			flag = true;
		}
		if(flag)
			period = per;
	}
	
}
