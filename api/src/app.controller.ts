import { Body, Controller, Get, Param, Put } from '@nestjs/common';
import { AppService } from './app.service';
import { Station } from './Station';
import { StationToView } from './StationToView';

@Controller('station')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getAllStation(): StationToView[] {
    return this.appService.getAllStationsToView();
  }
  @Get(':id')
  getStation(@Param('id') stationId): Station {
    return this.appService.getStation(stationId);
  }

  @Put(':id')
  setBookmarked(@Param('id') params, @Body() station: Station) {
    this.appService.setBookmarked(params, station);
  }
}
