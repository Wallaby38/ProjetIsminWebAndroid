import { Controller, Get } from '@nestjs/common';
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
}
