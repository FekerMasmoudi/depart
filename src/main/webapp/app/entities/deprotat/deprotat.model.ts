import dayjs from 'dayjs/esm';
import { IDepart } from 'app/entities/depart/depart.model';

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
  r_annul?: string | null;
  km?: number | null;
  motif_a?: number | null;
  observ?: string | null;
  recettesvoy?: number | null;
  nbrevoy?: number | null;
  paye?: number | null;
  cd1?: number | null;
  cd2?: number | null;
  cd3?: number | null;
  decmotifcha?: number | null;
  decmotifrea?: number | null;
  id_apex?: number | null;
  plusmoins?: string | null;
  a?: string | null;
  r?: string | null;
  depart?: Pick<IDepart, 'id'> | null;
}

export type NewDeprotat = Omit<IDeprotat, 'id'> & { id: null };
