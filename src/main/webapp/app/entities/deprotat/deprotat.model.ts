import dayjs from 'dayjs/esm';

export interface IDeprotat {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  dedated?: dayjs.Dayjs | null;
  denumdp?: number | null;
  decserv?: number | null;
  decoper?: string | null;
  decsean?: string | null;
  numrotat?: number | null;
  ligdeccent?: number | null;
  ligdecagenc?: number | null;
  denumli?: string | null;
  decstat?: string | null;
  decsta1?: string | null;
  matric?: number | null;
  matric1?: number | null;
  cdmac?: string | null;
  hdeparte?: dayjs.Dayjs | null;
  hretoure?: dayjs.Dayjs | null;
  harralle?: dayjs.Dayjs | null;
  harrrete?: dayjs.Dayjs | null;
  rannul?: string | null;
  km?: number | null;
  motifa?: number | null;
  observ?: string | null;
  recettesvoy?: number | null;
  nbrevoy?: number | null;
  paye?: number | null;
  cd1?: number | null;
  cd2?: number | null;
  cd3?: number | null;
  decmotifcha?: number | null;
  decmotifrea?: number | null;
  idapex?: number | null;
  plusmoins?: string | null;
  a?: string | null;
  r?: string | null;
}

export type NewDeprotat = Omit<IDeprotat, 'id'> & { id: null };
