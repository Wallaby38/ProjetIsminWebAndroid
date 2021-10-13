export interface Fields {
  ylatitude: number;
  xlongitude: number;
  type_prise: string;
  code_insee_commune: string;
  accessibilite: string;
  n_amenageur: string;
  region: string;
  ad_station: string;
  date_maj: string;
  geo_point_borne: number[];
  source: string;
  puiss_max: number;
  n_station: string;
  id_station: string;
  n_enseigne: string;
  code_insee: number;
  id_pdc: string;
  n_operateur: string;
  acces_recharge: string;
  nbre_pdc: number;
}

export interface Geometry {
  type: string;
  coordinates: number[];
}

export interface RawData {
  datasetid: string;
  recordid: string;
  fields: Fields;
  geometry: Geometry;
  record_timestamp: Date;
}
