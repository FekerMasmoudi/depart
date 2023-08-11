import dayjs from 'dayjs/esm';

export interface ILigne {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  denumli?: string | null;
  dectyli?: string | null;
  dectyta?: string | null;
  denomli?: string | null;
  dectyeq?: string | null;
  denbrkm?: number | null;
  detparc?: number | null;
  dedural?: number | null;
  dedurrt?: number | null;
  detrjva?: number | null;
  detrjvr?: number | null;
  depiste?: number | null;
  statlig?: string | null;
  lig?: string | null;
  lig1?: number | null;
  valide?: string | null;
  denumli2?: string | null;
  kml?: string | null;
  kmlContentType?: string | null;
  description?: string | null;
  mimtype?: string | null;
  filename?: string | null;
  charset?: string | null;
  lastupdate?: dayjs.Dayjs | null;
}

export type NewLigne = Omit<ILigne, 'id'> & { id: null };
