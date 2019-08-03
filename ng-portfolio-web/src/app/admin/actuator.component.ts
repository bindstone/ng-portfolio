import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActuatorService} from "../service/actuator.service";
import {Observable, Subscription} from "rxjs";
import {AdminService} from "../service/admin.service";

@Component({
  selector: 'bs-actuator',
  templateUrl: './actuator.component.html',
  styleUrls: ['./actuator.component.css'],
  providers: [ActuatorService, AdminService]
})
export class ActuatorComponent implements OnInit, OnDestroy {

  public info: any;
  public health: any;
  public metricsList: String[];
  private infoSubscription: Subscription;
  private infoObservable: Observable<any>;
  private healthSubscription: Subscription;
  private healthObservable: Observable<any>;
  private metricsSubscription: Subscription;
  private metricsObservable: Observable<any>;

  constructor(private actuatorService: ActuatorService, private adminService: AdminService) {
    this.health = {};
    this.metricsList = [];
  }

  ngOnInit() {
    this.infoObservable = this.actuatorService.getInfo();
    this.infoSubscription = this.infoObservable.subscribe(
      value => this.info = value,
      err => this.solveError(err)
    );

    this.healthObservable = this.actuatorService.getHealth();
    this.healthSubscription = this.healthObservable.subscribe(
      value => this.health = value,
      err => this.solveError(err)
    );

    this.metricsObservable = this.actuatorService.getMetrics();
    this.metricsSubscription = this.metricsObservable.subscribe(
      value => this.solveMetricsResult(value),
      err => this.solveError(err)
    );
  }

  public generate() {
    this.adminService.generateTestData().subscribe();
  }

  ngOnDestroy(): void {
    if (this.infoSubscription) {
      this.infoSubscription.unsubscribe();
    }
    if (this.healthSubscription) {
      this.healthSubscription.unsubscribe();
    }
    if (this.metricsSubscription) {
      this.metricsSubscription.unsubscribe();
    }
  }

  private solveMetricsResult(value: any) {
    if (value && value.names) {
      this.metricsList = value.names;
    }
  }

  private solveError(value: any) {
  }

}
