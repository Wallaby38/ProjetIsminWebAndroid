import { HttpService, Injectable, OnModuleInit } from '@nestjs/common';
import { Station } from './Station';
import { RawStation } from './RawStation';
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

  toStation(rawData: RawStation[]): void {
    rawData.forEach((rawStation) => {
      const station: Station = {
        id_station: rawStation.fields.id_station,
        ylatitude: rawStation.fields.ylatitude,
        xlongitude: rawStation.fields.xlongitude,
        type_prise: rawStation.fields.type_prise,
        accessibilite: rawStation.fields.accessibilite,
        region: rawStation.fields.region,
        ad_station: rawStation.fields.ad_station,
        puiss_max: rawStation.fields.puiss_max,
        acces_recharge: rawStation.fields.acces_recharge,
        nbre_pdc: rawStation.fields.nbre_pdc,
      };
      this.addStation(station);
    });
  }
  addStation(station: Station): void {
    this.stations.set(station.id_station, station);
  }

  getAllStations(): Station[] {
    return Array.from(this.stations.values());
  }
}
