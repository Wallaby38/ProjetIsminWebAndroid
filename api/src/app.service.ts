import { HttpService, Injectable, OnModuleInit } from '@nestjs/common';
import { Station } from './Station';
import { RawData } from './RawData';
import { raw } from 'express';

@Injectable()
export class AppService implements OnModuleInit {
  private readonly stations = new Map<string, Station>();
  constructor(private httpService: HttpService) {}
  onModuleInit(): void {
    const promise = this.httpService
      .get(
        'https://opendata.reseaux-energies.fr/explore/dataset/bornes-irve/download/?format=json&timezone=Europe/Berlin&lang=en',
      )
      .subscribe((response) => this.toStation(response.data));
  }

  toStation(rawData: RawData[]): void {
    rawData.forEach((rawStation) => this.addStation(rawStation.fields));
  }
  addStation(station: Station): void {
    this.stations.set(station.id_station, station);
  }

  getAllStations(): Station[] {
    return Array.from(this.stations.values());
  }
}
