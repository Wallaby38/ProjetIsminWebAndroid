import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { Station } from './Station';

@Controller('station')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getAllStation(): Station[] {
    return this.appService.getAllStations();
  }
}
