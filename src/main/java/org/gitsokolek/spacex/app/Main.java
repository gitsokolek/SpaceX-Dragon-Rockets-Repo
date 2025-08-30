package org.gitsokolek.spacex.app;

import org.gitsokolek.spacex.bootstrap.SampleStore;
import org.gitsokolek.spacex.report.ReportGenerator;

public class Main
{
	public static void main(String[] args)
	{
		SampleStore     store     = SampleStore.create();
		ReportGenerator generator = new ReportGenerator();
		String          report    = generator.generate(store.missionRepo(), store.dragonRepo());
		System.out.print(report);
	}
}
