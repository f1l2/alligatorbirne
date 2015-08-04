package iot.device.repo;

public class Job implements Runnable{

	private JobJPA job;
	
	public Job(JobJPA job){
		this.job = job;
	}
	
	@Override
	public void run() {
		
		
		for (;;) {
			System.out.println(job.getEpId() + " is running");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
